# Code Fun Do 2019

## Team Pepperoni

![Voter placing his vote](https://github.com/akshatha-k/Code.fun.do.2019/blob/master/pictures/person_voting.jpeg)

*Prototype testing*


Earlier this year, India recorded the largest number of eligible citizens to vote for the 
national government making it the biggest democratic election in the world. At such a large scale, 
every small decision affecting this process makes a huge difference. Every vote can decide the future of our nation.

### Current Problems and Issues
The biggest problem we face today, is the tampering of votes by polling booths being taken over 	by various organizations to benefit a political party.  Alleged cases such as [this](https://www.livemint.com/Politics/fIKiRvhaDSieYz25Lm8vRM/EVM-tampering-case-Supreme-Court-issues-notice-to-Centre-E.html) are not unheard of. In a country as large as India, such actions take away the right to vote freely from many citizens.

Another problem is low voter turnout. The 2 major reasons being, 
	i) Time Constraints
	ii) Assigned Poll Station is far from the current residence of the citizen.

This year, many eligible citizens were first-time voters who study in colleges and cannot afford to travel
back to their place of residence. 
### Our Proposal

![SEVAM](https://github.com/akshatha-k/Code.fun.do.2019/blob/master/pictures/SEVAM.jpeg)*Our EVM Model*

  A Blockchain is a chain of entries in a ledger which is shared among a group of users who
authorize any transaction through the Blockchain. Each block contains a cryptographic hash of the 
previous block, a timestamp, and transaction data. 

By leveraging the power of the cloud and Security of the Blockchain Technology, we aim to  
create a system which prevents vote tampering and allows people to vote freely. Our system also displays statistics 
such as the promises made and fulfilled for each candidate taking part in the elections in a particular region and is displayed to the 
voter according to the region of recidency.
	
Our system comprises of the following modifications to the normal polling stations -

#### Security
![Security system flowchart](https://github.com/akshatha-k/Code.fun.do.2019/blob/master/pictures/methodology.png)

*Flowchart of the Security system*
- A biometric fingerprint verification to confirm the vote by the citizen deployed and connected using Azure IoT Hub
- Detection of illegal equipment and weaponry using Image Recognition Technology and Deep Learning. We employ the sightengine API and the Vision API by using their pre-trained deep-learning model for the detection and securing the actual process of voting by feeding the program live-feed of the process and making it predict any illegal activity at the booth.
- OpenPose to detect tampering of the EVM in poll boths, and mainly, in EVM storage rooms.

![OpenPose](https://github.com/akshatha-k/Code.fun.do.2019/blob/master/pictures/evm_pose.png)

*OpenPose based surveillance*

#### Blockchain
- Acceptance, Storage and calculation of votes per party using Azure Blockchain. 
- Visualizing voter data from various poll stations using PowerBI. 

#### Voter Data Analysis and Prediction using ML
- Analysing Previous voter data from government datasets, using Machine Learning and Deep Learning techniques, to generate predictions regarding the following:
1) Number of voters from every state and constituency respectively next year . Number of voters classified as women, SC/ST etc. will also be predicted. 
2) Information about the correlation between number of voters ( or women voters) and the literacy , poverty, state income, rainfall and number of govt. schemes in that state
The above techniques will enable the election commission to allocate resources as well as security more effectively. This will also raise awareness about areas and communities with low voter percentage, as well as reasons that lead to low/high voter turnout, so the govt. can work to increase voter awareness.
3) Predict expense to be incurred to the govt. in the next elections based on previous voting trends. 

![Linear Regression model to predict voter turnout](https://github.com/akshatha-k/Code.fun.do.2019/blob/master/pictures/linear%20reg%20test%20set.png)

*Linear regression model to predict voter turnout . Note the outlier in 1985*

#### Working:

![Prototype](https://github.com/akshatha-k/Code.fun.do.2019/blob/master/pictures/Prototype.jpeg)*Prototype Setup*

The EVM is constructed around a small embedded microcomputer that is connected to Azure Blockchain and a 
database which holds the unique Identification Data of the people in that region.

Once the citizen chooses the party, the biometric fingerprint scanner
waits for verification and once verified, the vote is permanently stored in the district blockchain.
(Assuming fingerprint data is already provided by the Unique Identification Authority of the country.)

Each poll station in a region (like a district) maintains a copy of the ledger for that region. 
All the votes in a particular region is then totalled to compute the national total. 
	
The cameras setup at the poll station continuously scan for any illegal equipment / weapons.
The Electronic Voting Machine (EVM) accepts votes if the camera detects no such equipment 
that is flagged illegal. In case of a flagged illegal equipment, an alert is sent to the authorised personnel. The system automatically stops accepting further votes until
the required personnel arrive and activate the system again with increased security. 
Our system can help the personnel conducting elections in a region to help it go fairly. 

The system also helps people who are away from their areas of recidence to vote for their representative.
Unless the citizen is confirmed permanent residence somewhere else, the system allows him/her to vote for
their representative upon logging in to an EVM anywhere in the country. The EVM displays various statistics about all the candidates taking part in the election in that region which helps the voter to decide. A query is passed through the system network
which then sends the data of the voter to the blockchain which holds data of the region he/she is 
registered to reside at. An entry is made in the blockchain of that district. 

A real-time algorithm implementing open pose is deployed in EVM storage rooms as well as polling stations. It raises an alert to the authorised personnel if anybody is detected to hamper the EVM. This is performed by estimating the coordinates of the person w.r.t the evm and an alert is generated if the person is detected within a set threshold distance.

We also plan to create an application which automatically sorts the voters every day based on their preffered
time to vote (given they already mentioned it in the voters online portal) and their distance from the nearest
poll station. This helps to minimize the queues in a poll station and evenly distribute the load among poll-stations
in a region.

Our system tries to make voting more convenient by allowing people in a region to vote in any poll station in that region
and distant - voters to vote for their representatives at their area of recidency. All of this is made possible by the 
our voting portal which runs on all EVMs that upon login, shows the voter, the parties of his/her registered area
of residency.


### Technologies Used
  Azure Blockchain, Azure IoT Hub, Embedded microcomputers to connect the EVMs to the Blockchain, SQL, TensorFlow, Python and C++, OpenPose, online Deep-learning APIs (sightengine API), Government of India election dataset
 
### References
[Microsoft Azure Workbench](https://docs.microsoft.com/en-us/azure/blockchain/workbench/)

[OpenPose](https://github.com/CMU-Perceptual-Computing-Lab/openpose)

[Government Election Data set](https://data.gov.in)

[Arduino](https://www.arduino.cc)

[Raspberry Pi 3](https://www.raspberrypi.org/products/raspberry-pi-3-model-b/)

[Finger Print Scanner](https://data.gov.in)
 
