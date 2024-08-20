SOURCES=\
	chunk.c \
	debug.c \
	main.c \
	memory.c \
	value.c \
	vm.c \

bin/b: $(SOURCES)
	cc -o bin/b -g -Wall $(SOURCES) && bin/b

clean:
	rm -rf bin/*
