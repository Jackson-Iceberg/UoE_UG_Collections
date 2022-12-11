-- Inf2d Assignment 1 2019-2020
-- Matriculation number: s1862323
-- Name: HAO ZHOU
-- {-# OPTIONS -Wall #-}


module Inf2d1 where

import Data.List (sortBy, elemIndices, elemIndex)
import ConnectFourWithTwist




{- NOTES:

-- DO NOT CHANGE THE NAMES OR TYPE DEFINITIONS OF THE FUNCTIONS!
You can write new auxillary functions, but don't change the names or type definitions
of the functions which you are asked to implement.

-- Comment your code.

-- You should submit this file when you have finished the assignment.

-- The deadline is the  10th March 2020 at 3pm.

-- See the assignment sheet and document files for more information on the predefined game functions.

-- See the README for description of a user interface to test your code.

-- See www.haskell.org for haskell revision.

-- Useful haskell topics, which you should revise:
-- Recursion
-- The Maybe monad
-- Higher-order functions
-- List processing functions: map, fold, filter, sortBy ...

-- See Russell and Norvig Chapters 3 for search algorithms,
-- and Chapter 5 for game search algorithms.

-}

-- Section 1: Uniform Search

-- 6 x 6 grid search states

-- The Node type defines the position of the robot on the grid.
-- The Branch type synonym defines the branch of search through the grid.
type Node = Int
type Branch = [Node]
type Graph= [Node]

numNodes::Int
numNodes = 4


-- The next function should return all the possible continuations of input search branch through the grid.
-- Remember that the robot can only move up, down, left and right, and can't move outside the grid.
-- The current location of the robot is the head of the input branch.
-- Your function should return an empty list if the input search branch is empty.
-- This implementation of next function does not backtrace branches.

--helper function for next
graph_drop :: Node -> Graph -> Graph
graph_drop n graph = drop (n*numNodes) graph

--helper function for next
graph_take :: Graph -> [Node]
graph_take graph_drop = take numNodes graph_drop

--helper function for next (Better style)
subList :: Node -> Graph -> [Node]
subList num graph = take numNodes (drop (num*numNodes) graph)


next::Branch -> Graph ->  [Branch]
next [] _ = []
next _ [] = []
next branch graph = [ b:branch | (a,b)<- zip subList_result [0..numNodes-1],a>0]
                    where subList_result = subList (head branch) graph



-- |The checkArrival function should return true if the current location of the robot is the destination, and false otherwise.
checkArrival::Node -> Node -> Bool
checkArrival destination curNode = destination == curNode


explored::Node-> [Node] ->Bool
explored point exploredList = point `elem` exploredList
    

-- Section 3 Uniformed Search
-- | Breadth-First Search
-- The breadthFirstSearch function should use the next function to expand a node,
-- and the checkArrival function to check whether a node is a destination position.
-- The function should search nodes using a breadth first search order.

--helper function for breadthFirstSearch
--checkDestination :: Node -> [Branch] -> Bool
--checkDestination destination (x:Branches) 
--                | checkDestination destination  destination == x || checkDestination destination Branches
--               | otherwise = checkDestination destination Branches
-- helper function for saving the space 

breadthFirstSearch::Graph -> Node->(Branch ->Graph -> [Branch])->[Branch]->[Node]->Maybe Branch
-- if frontier = EMPTY
breadthFirstSearch graph destination next [] exploredList = Nothing
breadthFirstSearch graph destination next (b:branches) exploredList 
    -- if the current node is the destination , return the branch.
    -- if problem.Goal-Test(node,state) then return solution(node)
    | checkArrival destination (head b) == True = Just b
    -- if the current node have already explored, (the child.state is in explored or frontier) then skip the whole branch.
    -- if child.state is in explored or frontier then skip it.
    | (explored (head b) exploredList == True) || or [head b `elem` bs | bs<-branches]  = breadthFirstSearch graph destination next branches exploredList 
    -- otherwise add the expanded branches to the back of the search list.(FIFO)
    | otherwise = breadthFirstSearch graph destination next (branches ++ next b graph) (head b : exploredList)
  

depthFirstSearch::Graph -> Node->(Branch ->Graph -> [Branch])->[Branch]->[Node]->Maybe Branch
-- if frontier = EMPTY
depthFirstSearch graph destination next [] exploredList = Nothing
depthFirstSearch graph destination next (b:branches) exploredList 
    -- if the current node is the destination , return the branch which the node is in.
    -- if problem.Goal-Test(node,state) then return solution(node)
    | checkArrival destination (head b) == True = Just b
    -- if the current node have already explored, (the child.state is in explored or frontier) skip the whole branch.
    -- if child.state is in explored or frontier then skip it.
    | explored (head b) exploredList == True || or [head b `elem` bs | bs<-branches] = depthFirstSearch graph destination next branches exploredList 
    --otherwise add the expanded branches to the head of the search list.(LIFO)
    | otherwise = depthFirstSearch graph destination next (next b graph ++ branches) (head b : exploredList)



-- | Depth-Limited Search
-- The depthLimitedSearch function is similiar to the depthFirstSearch function,
-- except its search is limited to a pre-determined depth, d, in the search tree.
depthLimitedSearch::Graph ->Node->(Branch ->Graph-> [Branch])->[Branch]-> Int->[Node]-> Maybe Branch
-- if frontier = EMPTY
depthLimitedSearch graph destination next [] depth exploredList = Nothing
--(if depth <= 0) then depthLimitedSearch graph destination next (b:branches) depth exploredList = Nothing
depthLimitedSearch graph destination next branches 0 exploredList = Nothing
depthLimitedSearch graph destination next (b:branches) depth exploredList 
    -- very similar like the depthFirstSearch
    -- if the current node is the destination , return the branch which the node is in.
    -- if problem.Goal-Test(node,state) then return solution(node)
    | checkArrival destination (head b) == True = Just b
    -- if the depth of a branch has reached the pre-set depth and not reached the destination,skip the whole branch. 
    -- if the current node have already explored, (the child.state is in explored or frontier) skip the whole branch.
    | depth == length b -1 || explored (head b) exploredList == True || or [head b `elem` bs | bs<-branches] = depthLimitedSearch graph destination next branches depth exploredList
    -- otherwise add the expanded branches to the head of the search list.(LIFO)
    | otherwise = depthLimitedSearch graph destination next (next b graph ++ branches) depth (head b : exploredList)


-- | Section 4: Informed search
-- | AStar Helper Functions
-- | The cost function calculates the current cost of a trace. The cost for a single transition is given in the adjacency matrix.
-- The cost of a whole trace is the sum of all relevant transition costs.

cost :: Graph -> Branch  -> Int
cost graph branch 
    | length branch <= 1 = 0
    | otherwise =  ((subList (last branch) graph) !! (last(init branch)))  + cost graph (init branch)
                
 -- test
 -- graph = [0,1,0,0,
 --          0,0,0,5,
 --          0,0,0,0,
 --          0,0,0,0]
 -- branch = [3,1,0]
 -- result = 6    
    
-- | The getHr function reads the heuristic for a node from a given heuristic table.
-- The heuristic table gives the heuristic (in this case straight line distance) and has one entry per node. It is ordered by node (e.g. the heuristic for node 0 can be found at index 0 ..)  
getHr:: [Int] ->Node -> Int
getHr hrTable node = head $ drop (node-1) hrTable



--helper function to sort the array which is from low to high
--newBranches :: Graph -> ([Int]->Node->Int) -> [Int] -> (Graph -> Branch -> Int) 
--totalCost graph getHr hrTable cost (b:branches) = undefined 
-- f (n) = g(n) + h(n)

heuristic :: Graph ->(Branch->Graph ->[Branch]) ->([Int]->Node->Int)->[Int]->(Graph->Branch->Int)-> [Branch]->[Branch]
heuristic graph next getHr hrTable cost branches =  map snd(sortBy(\(a,_) (b,_) -> compare a b) [ (fResult sb, sb) | sb <- branches])
        where fResult branches = cost graph branches + getHr hrTable (head branches)

-- | A* Search
-- The aStarSearch function uses the checkArrival function to check whether a node is a destination position,
---- and a combination of the cost and heuristic functions to determine the order in which nodes are searched.
---- Nodes with a lower heuristic value should be searched before nodes with a higher heuristic value.

aStarSearch::Graph->Node->(Branch->Graph->[Branch])->([Int]->Node->Int)->[Int]-> (Graph->Branch->Int)->[Branch]-> [Node]-> Maybe Branch
aStarSearch graph destination next getHr hrTable cost [] exploredList = Nothing
aStarSearch graph destination next getHr hrTable cost (b:branches) exploredList 
--- if the current node is the destination , return the branch which the node is in.
    | checkArrival destination (head b) == True = Just b
-- if the depth of a branch has reached the pre-set depth and not reached the destination,skip the whole branch. 
    | explored (head b) exploredList == True || or [head b `elem` bs | bs<-branches] =  aStarSearch graph destination next getHr hrTable cost branches exploredList 
-- go to the lowest cost path
    | otherwise =  aStarSearch graph destination next getHr hrTable cost (heuristic_result(next b graph ++ branches)) exploredList 
        where heuristic_result bs = (heuristic graph next getHr hrTable cost bs) 

      
 


-- | Section 5: Games
-- See ConnectFour.hs for more detail on  functions that might be helpful for your implementation. 
-- | Section 5.1 Connect Four with a Twist
-- The function determines the score of a terminal state, assigning it a value of +1, -1 or 0:

eval :: Game -> Int
eval game 
    | checkWin game 1  = 1
    | checkWin game 0  = -1
    | otherwise = 0

-- helper function for maxValue
compare_for_max :: Int -> [Game] -> Int -> Int -> Int
compare_for_max value [] alpha beta = value
compare_for_max value (g:games) alpha beta
    | value >= beta = value
    | otherwise = compare_for_max (max value alpha) games alpha beta
       where value = maximum(value,miniValue g alpha beta)

-- helper function called Max is for miniMax
maxValue :: Game -> Int -> Int -> Int
maxValue game alpha beta
-- if terminal-test(state) = true, return it
 | terminal game = eval game
 -- for loop
 | otherwise = compare_for_max alpha (moves game 1) alpha beta
    

--helper function for miniValue
compare_for_mini :: Int -> [Game] -> Int -> Int -> Int
compare_for_mini value [] alpha beta = value
compare_for_mini value (g:games) alpha beta
    | value <= alpha = value
    | otherwise = compare_for_mini (min value beta) games alpha beta
         where value = minimum(value,maxValue g alpha beta)


-- helper function called Mini is for miniMax
miniValue :: Game -> Int -> Int -> Int
miniValue game alpha beta 
-- if terminal-test(state) = true, return it
 | terminal game = eval game
  -- for loop
 | otherwise = compare_for_mini alpha (moves game 0) alpha beta
     


-- | The alphabeta function should return the minimax value using alphabeta pruning.
-- The eval function should be used to get the value of a terminal state. 
alphabeta:: Role -> Game -> Int
alphabeta  player game = maxValue game (-2) 2


-- | OPTIONAL!
-- You can try implementing this as a test for yourself or if you find alphabeta pruning too hard.
-- If you implement minimax instead of alphabeta, the maximum points you can get is 10% instead of 15%.
-- Note, we will only grade this function IF YOUR ALPHABETA FUNCTION IS EMPTY.
-- The minimax function should return the minimax value of the state (without alphabeta pruning).
-- The eval function should be used to get the value of a terminal state.

minimax:: Role -> Game -> Int
minimax player game 
    | terminal game == True = eval game 
  -- if the current player is human, find the maximum value of the nodes
  --the values of the nodes is depend on the moves of the next player.(computer)
    | player == 1 = maximum [minimax (switch player) p  | p <- moves game player ]
  -- if the current player is computer, find the minimum value of the nodes
  --the values of the nodes is depend on the moves of the next player.(human)
    | player == 0 = minimum [minimax (switch player) p  | p <- moves game player ]

-- | The alphabeta function should return the minimax value using alphabeta pruning.
--  The eval function should be used to get the value of a terminal state.

{- Auxiliary Functions
-- Include any auxiliary functions you need for your algorithms below.
-- For each function, state its purpose and comment adequately.
-- Functions which increase the complexity of the algorithm will not get additional scores
-}
