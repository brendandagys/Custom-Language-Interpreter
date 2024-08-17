package com.brendandagys.interpreter;

import java.util.List;
import java.util.Map;

class UserClass implements CustomCallable {
  final String name;
  private final Map<String, UserFunction> methods;

  UserClass(String name, Map<String, UserFunction> methods) {
    this.name = name;
    this.methods = methods;
  }

  UserFunction findMethod(String name) {
    if (methods.containsKey(name)) {
      return methods.get(name);
    }

    return null;
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
