package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockDA;
  private TorpedoStore mockDB;

  @Before
  public void init(){
    mockDA = mock(TorpedoStore.class);
    mockDB = mock(TorpedoStore.class);

    this.ship = new GT4500(mockDA,mockDB);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);


    when(mockDA.fire(1)).thenReturn(true);

    verify(mockDA, times(1)).fire(1);
    // Assert
  }

  @Test
  public void fireTorpedo_Single_Success_primaryEmpty(){
    // Arrange
    when(mockDA.isEmpty()).thenReturn(true);
    when(mockDB.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

//    ship.setPrimaryFiredLast(false);
    when(mockDB.fire(1)).thenReturn(true);


    verify(mockDB, times(1)).fire(1);
    // Assert
  }

  @Test
  public void fireTorpedo_Single_Success_secondaryEmpty(){
    // Arrange
    when(mockDA.isEmpty()).thenReturn(true);
    when(mockDB.isEmpty()).thenReturn(false);
    ship.setPrimaryFiredLast(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);


    when(mockDB.fire(1)).thenReturn(true);


    verify(mockDB, times(1)).fire(1);
    // Assert
  }

  @Test
  public void fireTorpedo_Single_Success_primaryEmpty_primaryFiredLast(){
    // Arrange
    when(mockDA.isEmpty()).thenReturn(false);
    when(mockDB.isEmpty()).thenReturn(true);
    ship.setPrimaryFiredLast(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);


    when(mockDA.fire(1)).thenReturn(true);


    verify(mockDA, times(1)).fire(1);
    // Assert
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);


    when(mockDA.fire(1)).thenReturn(true);
    verify(mockDA, times(0)).fire(1);
    // Assert
  }



}
