contract WalletContract {

    // logged events:
    // Funds has arrived into the wallet (record how much).
    event Deposit(address from, uint value);

    // gets called when no other function matches
    function() {
        // just being sent some cash?
        if (msg.value > 0)
            Deposit(msg.sender, msg.value);
    }
}