#ifndef compiler_vm_h
#define compiler_vm_h

#include "object.h"
#include "table.h"
#include "value.h"

#define FRAMES_MAX 64
#define STACK_MAX (FRAMES_MAX * UINT8_COUNT)

// Represents a single ongoing function call
typedef struct {
  ObjFunction* function;  // Function being called
  uint8_t* ip;            // Jump here when function returns
  Value* slots;           // Points to first slot that function can use
} CallFrame;

typedef struct {
  CallFrame frames[FRAMES_MAX];
  int frameCount;  // Current height of `CallFrame` stack

  Value stack[STACK_MAX];
  Value* stackTop;
  Table globals;
  Table strings;
  Obj* objects;  // Head of linked list
} VM;

typedef enum {
  INTERPRET_OK,
  INTERPRET_COMPILE_ERROR,
  INTERPRET_RUNTIME_ERROR
} InterpretResult;

extern VM vm;

void initVM();
void freeVM();
InterpretResult interpret(const char* source);
void push(Value value);
Value pop();

#endif
