contract RatingContract {
    mapping (address => int) public ratings;
    mapping (address => int) public weights;

    function rate(address fan, address musician) returns (bool) {
        if(weights[fan] == 0){
            //first vote
            weights[fan] = 11;
        }
        weights[fan] = weights[fan] - 1;
        if (weights[fan] == -1) {
            //bottom is reached, ignore
        } else {
            ratings[musician] += weights[fan];
        }
        return true;
    }

    function getRating(address musician) constant returns(int){
        return ratings[musician];
    }
}