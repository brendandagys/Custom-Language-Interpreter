package com.brendandagys.interpreter;

import java.util.List;

interface CallableFunction {
  int arity();

  Object call(Interpreter interpreter, List<Object> arguments);
}
