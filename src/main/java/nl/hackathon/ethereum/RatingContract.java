package nl.hackathon.ethereum;

import java.util.concurrent.CompletableFuture;

import org.adridadou.ethereum.values.EthAddress;

/**
 * Created by kivanov on 10/02/2017.
 */
public interface RatingContract {

    CompletableFuture<Boolean> vote(EthAddress key);

}
