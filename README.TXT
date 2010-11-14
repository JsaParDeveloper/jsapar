JSAPAR 2.0 EXPERIMENTAL
=======================

This project includes a root POM consisting of four projects:
* jsapar-exp-internal
* jsapar-exp-external
* jsapar-exp-deliverable
* jsapar-exp-tutorials

The jsapar-exp-internal project is for our own development and for experimenting with new code.
The jsapar-exp-external project is for external projects that have potential to be merged into the new JsaPar library.
An example of such a project is the JFileHelpers project.
The jsapar-exp-deliverable project is used to make a transition from the playground/experimental branch into the official jsapar 2.0 branch. Before a transistion/merge is done, the deliverable project must be cleaned up, fully javadoc-ed and LABELED in SVN!
The jsapar-exp-tutorials project is for studying tutorials and the code that comes with it. Code from the tutorials should not be
changed into jsapar code -> work out your own ideas in the jsapar-internal project!

All projects have NO dependencies between them! 

To build the project using Maven2, just compile the project from the ROOT directory of the project.
For developing within the Eclipse IDE, just give the Maven2 "mvn eclipse:eclipse" command from the ROOT directory of the project.
Next import the four projects into your eclipse workspace.

That's all folks!

/JsaPar Developer

