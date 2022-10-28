# Lec1
## Summary
Machine learning with Graphs   
▪ Applications and use cases  
Different types of tasks:  
▪ Node level  
▪ Edge level  
▪ Graph level  
Choice of a graph representation:   
▪ Directed, undirected, bipartite, weighted, adjacency matrix  

## Graph Concepts
degree of a node  
avg node degree  
in degree, out degree, total degree (source, sink)  
graph representations

## Graph Representations
Adj Matrix  
List of Edges  
Adj list  

## Types of graphs
Directed/Undirected  
spatial  
biparte (+ projected/folded)  
sparse (e.g. + large)  
weighted/unweighted  
self edges,multigraph  
connected/disconnected  


## Types of graphs in bio
* Disease Pathways  
Discovering disease pathways, which can be defined as sets of proteins associated with a given disease, is an important problem that has the potential to provide clinically actionable insights for disease diagnosis, prognosis, and treatment.  

* Networks of Neurons

* Regulatory Networks  
A gene regulatory network is a collection of molecular regulators that interact with each other and with other substances in the cell to govern the gene expression levels of mRNA and proteins which, in turn, determine the function of the cell. 

* Molecules

* Protein Folding Problem -> Spatial Graphs
Spatial graphs are defined by having nodes with spatial locations, usually given by coordinates in one, two or three dimensions. This small modification to aspatial graphs has profound effects on how these graphs are used and interpreted.

#### Networks
Networks are complex.  
 Arbitrary size and complex topological structure (i.e., no spatial locality like grids)  
 No fixed node ordering or reference point  
 Often dynamic and have multimodal features  

## Architecture of DL in graphs
Input: Network -> Graph Convolutions on Nodes -> Activation Function -> Regularization (e.g. dropout) -> ...Repeat last three steps... ->  
-> Predictions:   
Node labels (Node level)  
New links (Edge-level)  
Generated graphs (Graph-level prediction, Graph generation)   
Subgraphs (Community (subgraph) level)

**Nodes and Edge Attributes**  
Possible options:    
▪ Weight (e.g., frequency of communication)  
▪ Ranking (best friend, second best friend...)  
▪ Type (friend, relative, co-worker)  
▪ Sign: Friend vs. Foe, Trust vs. Distrust  
▪ Properties depending on the structure of the rest of the graph: Number of common friends  

## Predictions | These Graph ML tasks lead to high-impact applications!
///////------Also, check slides for really interesting examples of real-world applications------/////// 

 Node classification: Predict a property of a node   
▪ Example: Categorize online users / items   
 Link prediction: Predict whether there are missing links between two nodes  
▪ Example: Knowledge graph completion    
 Graph classification: Categorize different graphs   
▪ Example: Molecule property prediction  
 Clustering: Detect if nodes form a community   
▪ Example: Social circle detection   
 Other tasks:    
▪ Graph generation: Drug discovery   
▪ Graph evolution: Physical simulation   






