# Stanford_Graph_ML_CS224W
By Jure Leskovec  
https://web.stanford.edu/class/cs224w/   
https://www.youtube.com/playlist?list=PLoROMvodv4rPLKxIpqhjhPgdQy7imNkDn   
https://web.stanford.edu/class/cs224w/info.html  

''Complex domains have a rich relational structure, which can be represented as a relational graph.   
By explicitly modeling relationships we achieve better performance! Not everything can be represented as a sequence or a grid''

### Progress
Videos 6/60    
Slides 2/19      
Colabs 0/5  

## !
using effective features over graphs is the key to achieving good model performance

### Ideas
***BIO***   
*Drug Repurposing*      
*Drug Discovery*      
*Drug side effects*   
*Disease Pathways*   
*Protein Folding*   
*Protein Structure*   
*Molecule Property Prediction, Formation/Optimization Prediction*   
*Promising Molecule Prediction (e.g. antiobiotics)* 
*Networks of Neurons*   
*Regulatory Networks*   


***IDEAS AND IMPLEMENTATION***  
*Node classification: Predict a property of a node*  
▪ *Example: Categorize online users / items*  
*Link prediction: Predict whether there are missing links between two nodes*  
▪ *Example: Knowledge graph completion*  
*Graph classification: Categorize different graphs*  
▪ *Example: Molecule property prediction*  
*Clustering: Detect if nodes form a community*  
▪ *Example: Social circle detection*  
*Other tasks:*  
▪ *Graph generation: Drug discovery*  
▪ *Graph evolution: Physical simulation*  



### Additional Readings
Graph Representation Learning Book by Will Hamilton  
Research papers  
Documentation (PyTorch, PyG, GraphGym, opt: SNAP.PY, NetworkX)

Optional readings:  
Papers and pointers to additional literature  

### Prerequisites  
Good background in:  
▪ Machine Learning  
▪ Algorithms and graph theory   
▪ Probability and statistics  

Programming:  
▪ You should be able to write non-trivial programs (in Python)  
▪ Familiarity with PyTorch is a plus  

### Work
▪ Homework   
3 written homeworks    

▪ Coding assignments    
5 coding assignments using Google Colab  

▪ Course project  
Proposal, Final report, Poster    
Default project (predefined task) or Custom project (open-ended)  

▪ PyG/GraphGym code contribution   

▪ Study past exams

### Course Outline
1. Introduction; Machine Learning for Graphs  ☑️  
2. Traditional Methods for ML on Graphs  
3. Node Embeddings  
4. Link Analysis: PageRank  
5. Label Propagation for Node Classification  
6. Graph Neural Networks 1: GNN Model  
7. Graph Neural Networks 2: Design Space  
8. Applications of Graph Neural Networks  
9. Theory of Graph Neural Networks  
10. Knowledge Graph Embeddings  
11. Reasoning over Knowledge Graphs  
12. Frequent Subgraph Mining with GNNs  
13. Community Structure in Networks  
14. Traditional Generative Models for Graphs  
15. Deep Generative Models for Graphs  
16. Advanced Topics on GNNs  
17. Scaling Up GNNs  
18. Guest lecture: TBD  
19. GNNs for Science  

We are going to cover various topics in Machine Learning and Representation Learning for graph structured data:  
▪ Traditional methods: Graphlets, Graph Kernels  
▪ Methods for node embeddings: DeepWalk, Node2Vec  
▪ Graph Neural Networks: GCN, GraphSAGE, GAT, Theory of GNNs  
▪ Knowledge graphs and reasoning: TransE, BetaE ▪ Deep generative models for graphs: GraphRNN  

### Tools
We use PyG (PyTorch Geometric):  
▪ The ultimate library for Graph Neural Networks  

We further recommend:  
▪ GraphGym: Platform for designing Graph Neural Networks.  
▪ Modularized GNN implementation, simple hyperparameter tuning, flexible user customization   
Both platforms are very helpful for the course project (save your time & provide advanced GNN functionalities)  

Other network analytics tools: SNAP.PY, NetworkX


