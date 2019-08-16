# Azure Blockchain Workbench

## Description
The blockchain consists of 2 Application Role Holders: 
1. Voter
2. Poll Station Administrator

It consists of 3 functions: Initialize, Validate and Invalidate.
It consists of 4 states: Initial, Intermediate, Valid and Invalid.

Here we use a smart contract to keep track of all transactions.
## Workflow
Initial State of the transaction is Initial.
The Poll Station Administrator (PSA) calls the blockchain to initialize the voting procedure through an API call which passes the region name / pincode to the blockchain from the GUI application everytime a voter logs in successfully into the application. The state of the transaction is Intermediate now.

The voter is allowed to cast his/her vote through the GUI, which with another API call passes the name of the party as argument to the blockchain. This calls the Validate function and the vote gets validated. Transaction state is now valid.

In case of any Electronic Voting Machine (EVM) tampering or detection of illegal arms in the poll station by our Security System, an api call is made to the blockchain with a string argument holding the time and an integer indicating the reason to invalidate the vote. This invokes the Invalidate function which invalidates the vote. Transaction state is now Invalid.

### Files
.json file describes the structure of the blockchain.
.sol defines all the funcitons used.

### Future plans
- This blockchain system can be used in various states / group of regions to keep track of the voting data. This will reduce cost, mitigate risk and reimagine processes involved in large scale voting / polling in countries like India.
- Multiple poll stations can hold copies of the ledgers in a region.
- Blockchains in a state / group of regions can be decentralized, eliminating the need of internet connectivity.
