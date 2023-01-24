package com.example.murange.Config.oauth;

import com.example.murange.Domain.User;
import com.example.murange.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();

        OauthAttributes attributes = OauthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveUser(attributes);

        httpSession.setAttribute("loginUser", new SessionUser(user.getUsername(), user.getEmail(), user.getPicture(), user.getId()));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRole())),
                attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private User saveUser(OauthAttributes attributes){

        Optional<User> findUser = userRepository.findByEmail(attributes.getEmail());

        if (findUser.isEmpty()) {
            User user = attributes.toEntity();
            return userRepository.save(user);
        }
        return findUser.get();
    }
}