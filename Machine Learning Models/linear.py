# -*- coding: utf-8 -*-

import pandas as pd  
import numpy as np  
import matplotlib.pyplot as plt  
import seaborn as seabornInstance 
from sklearn.model_selection import train_test_split 
from sklearn.linear_model import LinearRegression
from sklearn import metrics
dataset = pd.read_csv('datafile_2014.csv')
dataset.shape
dataset.describe()
a=dataset.head()
dataset.plot(x='Year', y='Number of electors - Total', style='o')  
plt.title('Year vs Number of electors')  
plt.xlabel('Year')  
plt.ylabel('Number of electors')  
plt.show()
plt.figure(figsize=(15,10))
plt.tight_layout()
seabornInstance.distplot(dataset['Number of electors - Total'])
X = dataset['Year'].values.reshape(-1,1)
y = dataset['Number of electors - Total'].values.reshape(-1,1)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=0)
regressor = LinearRegression()  
regressor.fit(X_train, y_train) #training the algorithm
#To retrieve the intercept:
print(regressor.intercept_)
#For retrieving the slope:
print(regressor.coef_)
y_pred = regressor.predict(X_test)
df = pd.DataFrame({'Actual': y_test.flatten(), 'Predicted': y_pred.flatten()})
df
df1 = df.head(25)
df1.plot(kind='bar',figsize=(16,10))
plt.grid(which='major', linestyle='-', linewidth='0.5', color='green')
plt.grid(which='minor', linestyle=':', linewidth='0.5', color='black')
plt.show()
plt.scatter(X_test, y_test,  color='gray')
plt.plot(X_test, y_pred, color='red', linewidth=2)
plt.show()
