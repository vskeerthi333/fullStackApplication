package com.crio.xmeme.controller;

import com.crio.xmeme.entities.Meme;
import com.crio.xmeme.service.MemeServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class Controller {

    @Autowired
    MemeServiceHandler memeServiceHandler;

    @PostMapping("/memes")
    public String postMeme(@RequestBody @Valid Meme meme) {
        return String.format("{\"id\" : %d}", memeServiceHandler.addMeme(meme));
    }

    @GetMapping("/memes")
    public List<Meme> getMemes() {
        return memeServiceHandler.getLatestMemes(100);
    }

    @GetMapping("/memes/{id}")
    public Meme getMemeById(@PathVariable int id) {
        return memeServiceHandler.getMemeById(id);
    }

    @PatchMapping("/memes/{id}")
    public void update(@PathVariable int id, @RequestBody Meme meme) {
        memeServiceHandler.updateMeme(id, meme);
    }

}
