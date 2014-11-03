/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Marc de Verdelhan & respective authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.verdelhan.ta4j.indicators.trackers.bollingerbands;

import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;
import eu.verdelhan.ta4j.indicators.trackers.SMAIndicator;
import eu.verdelhan.ta4j.mocks.MockTimeSeries;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BollingerBandsMiddleIndicatorTest {
    private TimeSeries data;

    @Before
    public void setUp() {
        data = new MockTimeSeries(1, 2, 3, 4, 3, 4, 5, 4, 3, 3, 4, 3, 2);
    }

    @Test
    public void bollingerBandsMiddleUsingSMA() throws Exception {
        SMAIndicator sma = new SMAIndicator(new ClosePriceIndicator(data), 3);
        BollingerBandsMiddleIndicator bbmSMA = new BollingerBandsMiddleIndicator(sma);

        for (int i = 0; i < data.getSize(); i++) {
            assertEquals(sma.getValue(i), bbmSMA.getValue(i));
        }
    }

    @Test
    public void bollingerBandsLowerShouldWorkJumpingIndexes() {
        SMAIndicator sma = new SMAIndicator(new ClosePriceIndicator(data), 3);
        BollingerBandsMiddleIndicator bbmSMA = new BollingerBandsMiddleIndicator(sma);

        assertEquals(sma.getValue(6), bbmSMA.getValue(6));
        assertEquals(sma.getValue(0), bbmSMA.getValue(0));

    }
}
