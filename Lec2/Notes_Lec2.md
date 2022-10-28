# Lec2

## Traditional ML Pipeline
* Design features for nodes/links/graphs 
* Obtain features for all training data
* Train an ML model:   
▪ Random forest  
▪ SVM  
▪ Neural network, etc.  
* Apply the model:  
▪ Given a new node/link/graph, obtain its features and make a prediction   

## !
using effective features over graphs is the key to achieving good model performance  

Goal: Make predictions for a set of objects  
Design choices:  
 Features: d-dimensional vectors  
 Objects: Nodes, edges, sets of nodes,  
entire graphs  
 Objective function:  
▪ What task are we aiming to solve?  

## ML in Graphs
#### Node-level prediction   
Given: G(V, E)  
Learn a function: f:V->R

