contract RatingContract {
    //proxy contract for actual 'rating' contract
    function rate(address fan, address musician) returns (bool) {}
}

contract WalletContract {

    address public rating;
    address public musician;

    function WalletContract(address ratingAddress, address musicianAddress){
        rating = ratingAddress;
        musician = musicianAddress;
    }

    // gets called when no other function matches
    function() payable {
        // just being sent some cash?
        RatingContract ratingProxy = RatingContract(rating);
        if (!ratingProxy.rate(msg.sender, musician)){
            //smth bad
        }
    }
}