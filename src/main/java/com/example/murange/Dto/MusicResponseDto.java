package com.example.murange.Dto;

import com.example.murange.Domain.Music;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MusicResponseDto {

    private Long id;

    private String title;

    private String music_url;

    private String img_url;

    private String soundcloud_url;

    public MusicResponseDto(Music music) {
        this.id=music.getId();
        this.title=music.getTitle();
        this.music_url=music.getMusic_url();
        this.img_url=music.getImg_url();
        this.soundcloud_url=music.getSoundcloud_url();
    }

    public MusicResponseDto toMusicResponseDto(Music music) {
        MusicResponseDto  musicResponseDto = new MusicResponseDto();
        musicResponseDto.setId(music.getId());
        musicResponseDto.setTitle(music.getTitle());
        musicResponseDto.setMusic_url(music.getMusic_url());
        musicResponseDto.setImg_url(music.getImg_url());
        musicResponseDto.setSoundcloud_url(music.getSoundcloud_url());
        return musicResponseDto;
    }
}
