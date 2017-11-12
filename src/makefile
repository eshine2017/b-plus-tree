JC = javac
JFLAGS = -g
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	./NodeStructure/Pair.java \
	./NodeStructure/Node.java \
	./NodeStructure/IndexNode.java \
	./NodeStructure/DataNode.java \
	BPlusTree.java \
	treesearch.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) ./NodeStructure/*.class