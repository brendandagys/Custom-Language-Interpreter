package com.brendandagys.interpreter;

import java.util.List;

interface CustomCallable {
  int arity();

  Object call(Interpreter interpreter, List<Object> arguments);
}
