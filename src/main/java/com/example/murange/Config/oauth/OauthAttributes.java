package com.example.murange.Config.oauth;

import com.example.murange.Domain.User;
import com.example.murange.Domain.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OauthAttributes {

    public Map<String, Object> attributes;
    private Long id;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OauthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
                           String picture, Long id){
        this.id = id;
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OauthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OauthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){

        return OauthAttributes.builder()
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .name((String) attributes.get("name"))
                .email((String)attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .build();
    }

    public User toEntity(){
        return User.builder()
                .email(email)
                .username(name)
                .picture(picture)
                .role(Role.USER.getValue())
                .build();
    }
}