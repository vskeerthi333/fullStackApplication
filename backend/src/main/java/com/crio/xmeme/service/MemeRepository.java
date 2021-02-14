package com.crio.xmeme.service;

import com.crio.xmeme.entities.Meme;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
interface MemeRepository extends PagingAndSortingRepository<Meme, Integer> {
    @Query(value = "SELECT * FROM memes meme WHERE meme.name = ?1 and meme.caption = ?2 and meme.url = ?3", nativeQuery = true)
    Meme findMeme(String name, String caption, String url);
}
