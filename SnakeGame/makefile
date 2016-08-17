# super simple makefile
#got it from MVC examples zip provided by professor
# call it using 'make NAME=name_of_code_file_without_extension'
# (assumes a .java extension)
NAME = "snake"

all:
	@echo "Compiling..."
	javac *.java

run: all
	@echo "Running..."
	java $(NAME)

clean:
	rm -rf *.class
