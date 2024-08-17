package com.brendandagys.interpreter;

import java.util.List;
import java.util.Map;

class UserClass implements CustomCallable {
  final String name;

  UserClass(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public Object call(Interpreter interpreter, List<Object> arguments) {
    UserInstance instance = new UserInstance(this);
    return instance;
  }

  @Override
  public int arity() {
    return 0;
  }
}
