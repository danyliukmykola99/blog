package danyliuk.mykola.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "roles")
public class UserRolesConfiguration {

  private List <String> admins;

  public List <String> getAdmins() {
    return admins;
  }

  public void setAdmins(List <String> admins) {
    this.admins = admins;
  }
}
