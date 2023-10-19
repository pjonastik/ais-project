package org.example.product.memory;

import org.example.product.memory.exception.ComponentIllegalStateException;
import org.example.product.memory.exception.MemoryInvalidException;
import org.example.product.memory.exception.UseMemoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemoryTest {

    private Memorable memorable;

    @BeforeEach
    void setUp() {
        memorable = new Memory("Samsung", "x1", 500) {
            @Override
            public boolean isMountable() {
                return false; //dummy method
            }
            //Test double
            // Only for testing purposes - so I am able to test only abstract class logic here.
            // So I am not forced to use specic type derived from abstract class
        };
    }

    @Test
    void createdInvalidInstanceZeroTotalCapacity() {
        Assertions.assertThrows(MemoryInvalidException.class, () -> {
            new Memory("dummy", "dummy", 0) {
                @Override
                public boolean isMountable() {
                    return false; //dummy method
                }
                //Test double
            };
        });
    }

    @Test
    void createdInvalidInstanceNegativeTotalCapacity() {
        Assertions.assertThrows(MemoryInvalidException.class, () -> {
            new Memory("dummy", "dummy", -1) {
                @Override
                public boolean isMountable() {
                    return false; //dummy method
                }
                //Test double
            };
        });
    }

    @Test
    void canUseMemory() {
        assertThat(memorable.canUseMemory(500)).isTrue();
        assertThat(memorable.canUseMemory(501)).isFalse();
    }

    @Test
    void canRemoveMemory() {
        assertThat(memorable.canRemoveMemory(0)).isTrue();
        assertThat(memorable.canRemoveMemory(1)).isFalse();
    }

    @Test
    void useMemory() {
        memorable.useMemory(200);
        assertThat(memorable.getActualUsage()).isEqualTo(200);

        memorable.useMemory(200);
        assertThat(memorable.getActualUsage()).isEqualTo(400);

        memorable.useMemory(100);
        assertThat(memorable.getActualUsage()).isEqualTo(500);
    }

    @Test
    void useMemoryZeroIsPossible() {
        memorable.useMemory(0);

        assertThat(memorable.getActualUsage()).isEqualTo(0);
    }

    @Test
    void useMemory_cannotUserMoreThanIsTotalCapacity() {
        Assertions.assertThrows(UseMemoryException.class, () -> {
            memorable.useMemory(501);
        });
    }

    @Test
    void useMemory_cannotUseBrokenMemory() {
        Memory memory = new Memory("Samsung", "x1", 500) {
            @Override
            public boolean isMountable() {
                return false; //dummy method
            }
        };
        memory.setWorking(false);

        Assertions.assertThrows(ComponentIllegalStateException.class, () -> {
            memory.useMemory(0);
        });
    }


    @Test
    void removeMemory() {
        memorable.useMemory(500);

        memorable.removeMemory(200);
        assertThat(memorable.getActualUsage()).isEqualTo(300);

        memorable.removeMemory(200);
        assertThat(memorable.getActualUsage()).isEqualTo(100);

        memorable.removeMemory(100);
        assertThat(memorable.getActualUsage()).isEqualTo(0);
    }

    @Test
    void removeMemory_removeZeroIsPossible() {
        memorable.removeMemory(0);
    }

    @Test
    void removeMemory_cannotRemoveMoreThanActualUsageIs() {
        Memory memory = new Memory("Samsung", "x1", 500) {
            @Override
            public boolean isMountable() {
                return false; //dummy method
            }
        };
        memory.setWorking(false);

        Assertions.assertThrows(ComponentIllegalStateException.class, () -> {
            memory.removeMemory(0);
        });
    }

    @Test
    void getPercentageUsage() {
        assertThat(memorable.getPercentageUsage()).isEqualTo(0f);

        memorable.useMemory(250);

        assertThat(memorable.getPercentageUsage()).isEqualTo(0.5f);
    }

    @Test
    void getFreeCapacity() {
        assertThat(memorable.getFreeCapacity()).isEqualTo(memorable.getTotalCapacity());

        memorable.useMemory(100);

        assertThat(memorable.getFreeCapacity()).isEqualTo(400);
    }
}