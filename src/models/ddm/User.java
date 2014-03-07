package models.ddm;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class User {

private String firstName;
private String surName;
private Integer id;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getSurName() {
return surName;
}

public void setSurName(String surName) {
this.surName = surName;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}