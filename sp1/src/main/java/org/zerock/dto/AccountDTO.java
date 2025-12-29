package org.zerock.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class AccountDTO implements UserDetails {


  private String uid;
  private String upw;
  private String uname;
  private String email;
  private List<AccountRole> roleNames;           // USER, MANAGER, ADMIN
  
  public void addRole(AccountRole role) {
      if(roleNames == null) {
          roleNames = new ArrayList<AccountRole>();
      }
      roleNames.add(role);
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      
      if(roleNames == null || roleNames.size() == 0) {
          return List.of();
      }
      // USER, MANAGER, ADMIN
      return roleNames.stream().map(role -> 
                  new SimpleGrantedAuthority("ROLE_" + role.name()))
                  .collect(Collectors.toList());
  }
                   // Collectors -> import java.util.stream.Collectors;
  
  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return false;
  }


}
