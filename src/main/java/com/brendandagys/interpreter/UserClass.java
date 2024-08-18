package com.brendandagys.interpreter;

import java.util.List;
import java.util.Map;

class UserClass implements CustomCallable {
  final String name;
  final UserClass superclass;
  private final Map<String, UserFunction> methods;

  UserClass(String name, UserClass superclass, Map<String, UserFunction> methods) {
    this.name = name;
    this.superclass = superclass;
    this.methods = methods;
  }

  UserFunction findMethod(String name) {
    if (methods.containsKey(name)) {
      return methods.get(name);
    }

    if (superclass != null) {
      return superclass.findMethod(name);
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

    UserFunction initializer = findMethod("init");
    if (initializer != null) {
      initializer.bind(instance).call(interpreter, arguments);
    }

    return instance;
  }

  @Override
  public int arity() {
    UserFunction initializer = findMethod("init");

    if (initializer == null)
      return 0;

    return initializer.arity();
  }
}
