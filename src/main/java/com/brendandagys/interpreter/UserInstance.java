package com.brendandagys.interpreter;

import java.util.HashMap;
import java.util.Map;

class UserInstance {
  private UserClass class_;
  private final Map<String, Object> fields = new HashMap<>();

  UserInstance(UserClass class_) {
    this.class_ = class_;
  }

  Object get(Token name) {
    if (fields.containsKey(name.lexeme)) {
      return fields.get(name.lexeme);
    }

    throw new RuntimeError(name, "Undefined property: '" + name.lexeme + "'");
  }

  @Override
  public String toString() {
    return class_.name + " instance";
  }

  void set(Token name, Object value) {
    fields.put(name.lexeme, value);
  }
}
