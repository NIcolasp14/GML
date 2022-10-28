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

## Graph Node Features
Using effective features over graphs is the key to achieving good model performance  

Goal: Make predictions for a set of objects  
Design choices:  
* Features: d-dimensional vectors  
* Objects: Nodes, edges, sets of nodes,  
entire graphs  
* Objective function:  
What task are we aiming to solve?  

## ML in Graphs
### Node-level prediction   
Given: G(V, E)  
Learn a function: f:V->R

Goal: Characterize the structure and position of a node in the network:  
* Node degree   
The degree 𝑘𝑣 of node 𝑣 is the number of edges (neighboring nodes) the node has.  
Treats all neighboring nodes equally. 

* Node centrality  https://en.wikipedia.org/wiki/Centrality   
Node centrality 𝑐 takes the node importance 𝑣 in a graph into account.    
Different ways to model importance:  
▪ Eigenvector centrality   
▪ Engienvector centrality    
▪ Betweenness centrality   
▪ Closeness centrality      
▪ and many others... 

* Clustering coefficient  

* Graphlets  
 

   
