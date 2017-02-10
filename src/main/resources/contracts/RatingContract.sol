contract RatingContract {
    mapping (address => uint) public ratings;

    function vote(address key) returns (bool) {
        ratings[key] += 1;
        return true;
    }
}