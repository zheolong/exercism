'''
======================
3D surface (color map)
======================

Demonstrates plotting a 3D surface colored with the coolwarm color map.
The surface is made opaque by using antialiased=False.

Also demonstrates using the LinearLocator and custom formatting for the
z axis tick labels.
'''

from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt
from matplotlib import cm
from matplotlib.ticker import LinearLocator, FormatStrFormatter
import numpy as np


fig = plt.figure()
ax = fig.gca(projection='3d')

# Read data from csv file
data = np.genfromtxt('input.csv', delimiter=',', skip_header=1,
                     skip_footer=1, names=['x', 'y', 'z1', 'z2'])

# Make data.
X = data['x']
Y = data['y']
Z1 = data['z1']
Z2 = data['z2']

# Plot the surface.
surf1 = ax.plot_trisurf(X, Y, Z1, cmap=cm.coolwarm,
                       linewidth=0, antialiased=False)

surf2 = ax.plot_trisurf(X, Y, Z1, cmap=cm.coolwarm,
                       linewidth=0, antialiased=False)

# Customize the z axis.
ax.set_zlim(0.007, 0.013)
ax.zaxis.set_major_locator(LinearLocator(10))
ax.zaxis.set_major_formatter(FormatStrFormatter('%.06f'))

# Add a color bar which maps values to colors.
fig.colorbar(surf1, shrink=0.5, aspect=5)
#fig.colorbar(surf2, shrink=0.5, aspect=5)

ax.view_init(15, 180)

plt.show()

