package com.pushapp.jobplanet.mypushapp.utils;

/**
 * Created by jobplanet on 1/4/17.
 */

import java.util.Objects;

/**
 * GOF command pattern
 */
public interface Command {
    void execute(Object info);
}
