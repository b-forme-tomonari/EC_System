package com.example.demo.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.domain.model.MUser;

public class LoginUserDetails implements UserDetails {

    private final MUser loginUser; // ログインユーザー
    private final Collection<? extends GrantedAuthority> authorities; // ユーザー権限

    public LoginUserDetails(MUser loginUser) {
        this.loginUser = loginUser;
        // ユーザー権限取得
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(loginUser.getRoleName()));
        this.authorities = authorities;
    }

    /**
     * ログインユーザーを取得
     * @return ユーザー
     */
    public MUser getLoginUser() {
        return loginUser;
    }

    /**
     * ユーザー権限
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * ユーザー認証のパスワード
     */
    public String getPassword() {
        return loginUser.getPassword();
    }

    /**
     * ユーザー認証のユーザー名
     */
    public String getUsername() {
        return loginUser.getUserId();
    }

    /**
     * ユーザーアカウントの有効期限
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * ユーザーのロック状態
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * ユーザーパスワードの有効期限
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *  ユーザーの有効か無効
     */
    public boolean isEnabled() {
        return true;
    }
}
