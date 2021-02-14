package com.crio.xmeme.service;

import com.crio.xmeme.entities.Meme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MemeServiceHandler {

    @Autowired
    private MemeRepository memeRepository;

    private final Pattern urlPattern = Pattern.compile("(http|https):\\/\\/(www)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");

    public MemeServiceHandler() {
    }

    public Meme getMemeById(int id) {
        Optional<Meme> meme = memeRepository.findById(id);
        if (meme.isPresent())
            return meme.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The meme you are looking for is not found");
    }

    public int addMeme(Meme meme) {
        isAllFieldExists(meme);
        checkURL(meme.getUrl());
        checkIfExists(meme);
        return memeRepository.save(meme).getId();
    }

    public List<Meme> getLatestMemes(int pageSize) {
         Pageable paging = PageRequest.of(0, pageSize, Sort.by("id").descending());
         Page<Meme> memes  = memeRepository.findAll(paging);
         return memes.toList();
    }

    public void updateMeme(int id, Meme meme) {
        Meme existedMeme = getMemeById(id);

        if (meme.getName() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is not allowed to modify");

        if (meme.getUrl() != null) {
            checkURL(meme.getUrl());
            existedMeme.setUrl(meme.getUrl());
        }
        if (meme.getCaption() != null) {
            existedMeme.setCaption(meme.getCaption());
        }
        checkIfExists(existedMeme);

        memeRepository.save(existedMeme);
    }

    private void checkURL(String URL) {
        Matcher matcher = urlPattern.matcher(URL);
        if (!matcher.matches())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "URL format is Incorrect");
    }

    private void checkIfExists(Meme meme) {
        Meme existedMeme = memeRepository.findMeme(meme.getName(), meme.getCaption(), meme.getUrl());
        if (existedMeme != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Meme already exists");
    }

    private void isAllFieldExists(Meme meme) {
        if (meme.getName() != null && meme.getCaption() != null && meme.getUrl() != null) return;
        String errorMsg  = "";
        if (meme.getName() == null) errorMsg = "Name ";
        if (meme.getCaption() == null) errorMsg = "Caption ";
        if (meme.getUrl() == null) errorMsg = "url ";

        errorMsg = "fields are absent";
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);

    }
}
