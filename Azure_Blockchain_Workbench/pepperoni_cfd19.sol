pragma solidity >=0.4.25 <0.6.0;

contract pepperoni_cfd19

{

    enum StateType { 

      Initial,

      Valid,

      Invalid,

      Intermediate

    }

    address public InstanceVoter;

    string public Candidate_Voted;

    StateType public State;



    address public InstanceADM;

    string public Region_Name;

    constructor() public

    {

        InstanceADM = msg.sender;

        State = StateType.Initial;

    }

    function Initiate(string region_Name) public 

    {

        if (State != StateType.Initial){
            revert();
        }
        

        if (InstanceADM != msg.sender){
            revert();
        }



        InstanceVoter = msg.sender;

        Region_Name = region_Name;

        State = StateType.Intermediate;

    }



    function Invalidate(string Error_Code) public

    {

        if ( State != StateType.Intermediate ){
            revert();
        }

        if (InstanceADM != msg.sender){
            revert();
        }

        State = StateType.Invalid;

    }



    function Validate(string Candidate_Name) public

    {

        if (InstanceVoter != msg.sender){
            revert();
        }

        Candidate_Voted = Candidate_Name;
        State = StateType.Valid;

    }

}