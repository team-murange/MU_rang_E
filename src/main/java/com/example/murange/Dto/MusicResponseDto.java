package com.example.murange.Dto;

import com.example.murange.Domain.Music;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MusicResponseDto {

    private String title;

    private String music_url;

    private String img_url;

    public MusicResponseDto(Music music) {
        this.title = music.getTitle();
        this.music_url = music.getMusic_url();
        this.img_url = music.getImg_url();
    }
}
