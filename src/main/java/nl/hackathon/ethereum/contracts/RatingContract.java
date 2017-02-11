package nl.hackathon.ethereum.contracts;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import org.adridadou.ethereum.values.EthAddress;

/**
 * Created by kivanov on 10/02/2017.
 */
public interface RatingContract {

    BigInteger ratings(EthAddress address);

    CompletableFuture<Boolean> rate(EthAddress fan, EthAddress musician);

}
