SOURCES=\
	chunk.c \
	compiler.c \
	debug.c \
	main.c \
	memory.c \
	scanner.c \
	value.c \
	vm.c \
	object.c \

bin/b: $(SOURCES)
	cc -o bin/b -g -Wall $(SOURCES) && bin/b

clean:
	rm -rf bin/*
