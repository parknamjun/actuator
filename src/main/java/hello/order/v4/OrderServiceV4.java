package hello.order.v4;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 메트릭 등록4 - @Timed
 */
@Timed("my.order")
@Slf4j
public class OrderServiceV4 implements OrderService {

    private AtomicInteger stock = new AtomicInteger(100);

    @Counted("my.order")
    @Override
    public void order() {
        log.info("주문");
        stock.decrementAndGet();
        sleep(100);
    }


    @Counted("my.order")
    @Override
    public void cancel() {
        log.info("취소");
        stock.decrementAndGet();
        sleep(100);
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }


    private static void sleep(int sleep) {
        try {
            Thread.sleep(sleep + new Random().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
