SRC=elemenTerra


run: compile
	java $(SRC)/Game

compile: clean
	javac $(SRC)/*.java

clean:
	rm -rf $(SRC)/*.class
	rm -rf $(SRC)/*/*.class
	rm -rf $(SRC)/*/*/*.class
	rm -rf $(SRC)/*~
	rm -rf $(SRC)/*/*~
	rm -rf $(SRC)/*/*/*~