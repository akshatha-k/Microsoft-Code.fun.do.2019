# Code Fun Do 2019

## Team Pepperoni

Earlier this year, India faced the largest number of eligible citizens to vote for the 
national government making it the biggest democratic elections in the world. At such a large scale, 
every small decision makes a huge difference. Every vote can decide the future of the nation.
### Current Problems and Issues

The biggest problem we face today, is the tampering of votes by Manipulation of the Public or
	polling booths being taken over by various organizations to benifit a political party. 
	In a country as large as India, such actions take away the right to vote freely from many citizens.

Another problem is people who don't vote. The 2 major reasons for this is, 
	i) Timely Constraints
	ii) Assigned Poll Station is far from the current residence of the citizen.

This year, many eligible citizens were first-time voters who study in college and cannot afford to travel
back to their place of recidence. 
### Our Proposal
  A Blockchain is a chain of entries in a ledger which is shared among a group of users who
authorize any transaction through the Blockchain. Each block contains a cryptographic hash of the 
previous block, a timestamp, and transaction data. 

By leveraging the power of the cloud and Security of the Blockchain Technology, we aim to  
create a system which prevents vote tampering and allows people to vote freely.
	
Our system consits of the normal poll station, with the following modifications -
	
- A biometric fingerprint verification to confirm the vote by the citizen deployed and connected using Azure IoT Hub
- Detection of illegal equipment and weapony using Image Recognition Technology and Deep Learning using Azure Kubernetes
- Acceptance, Storage and calculation of votes per party using Azure Blockchain. 
- Visualizing voter data from various poll stations using PowerBI. 
- Releasing a report and predicting the women voters thing (AKSHATHA PLEASE FINISH IT OFF)

#### Working:

The cameras setup at the poll station continuously scan for any illegal equipment / weapons.
The Electronic Voting Machine (EVM) accepts votes if the camera detects no such equipment 
that is flagged illegal. Once the citizen chooses the party, the biometric fingerprint scanner
waits for verification and once verified, the vote is permanently stored in the poll-station blockchain.
Assuming fingerprint data is already provided by the Unique Identification Authority of the country.

Each poll station in a region (like a district) maintains a copy of the ledger for that region. 
All the votes in a particular region is then totalled to compute the national total. 
	
In case of a flagged illegal equipment, the system automatically stops accepting further votes until
the required personnel arrive and activate the system again with increased security. 
Our system can help the personnel conducting elections in a region to help it go fairly. 
Increasing security depends purely on the decision of the personnel conducting the elections.

The system also helps people who are away from their areas of recidence to vote for their representative.
Unless the citizen is confirmed permanent residence somewhere else, the system allows him/her to vote for
their representative upon logging in to an EVM anywhere in the country. A query is passed through the system network
which then sends the data of the voter to the blockchain which holds data of the region he/she is 
registered to reside at. An entry is made in the blockchain of that district.  

We also plan to create an application which automatically sorts the voters every day based on their preffered
time to vote (given they already mentioned it in the voters online portal) and their distance from the nearest
poll station. This helps to minimize the queues in a poll station and evenly distribute the load among poll-stations
in a region.

Our system tries to make voting more convenient by allowing people in a region to vote in any poll station in that region
and distant - voters to vote for their representatives at their area of recidency. All of this is made possible by the 
our voting portal which runs on all EVMs that upon login, shows the voter, the parties of his/her registered area
of residency.


### Technologies Used
  Azure Blockchain, Azure IoT Hub, PowerBI, Azure Kubernetes, Embedded microcomputers to host the image recognition algorithms locally. TensorFlow, Python and C++
	
