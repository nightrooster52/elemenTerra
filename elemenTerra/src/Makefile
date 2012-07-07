SRC=elementerra


run: compile
	java $(SRC)/Game

compile: clean
	javac $(SRC)/*.java

clean:
	rm $(SRC)/*.class
	rm $(SRC)/*/*.class
	rm $(SRC)/*/*/*.class
	rm $(SRC)/*~
	rm $(SRC)/*/*~
	rm $(SRC)/*/*/*~