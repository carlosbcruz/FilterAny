/**
 * The program(s) herein may be used  freely
 * for personal use only. It may not be sold, 
 * rented, leased, sublicensed  or  modified 
 * without permission of  the  author.  
 * 
 *           www.carlosbcruz.com
 *           
 * This program is provided "AS IS"  without 
 * warranty of any kind. In no event I  will
 * be liable  for  any  direct  or  indirect 
 * damage.
 */

package com.carlosbcruz.util;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Provides network methods.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class Network {

	/**
	 * Store information about the network.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public static class NetworkInformation implements Serializable {

		private static final long serialVersionUID = 1L;

		// The IP number of the computer.
		private byte[] ipAddress = null;
		// The host name of the computer.
		private String hostName = new String();
		// The host IP address in text format.
		private String hostAddress = new String();
		// The canonical host name.
		private String canonicalHostName = new String();
		// List of inet addresses.
		private List<InetAddress> inetAddress = new ArrayList<>();
		// List of all network interfaces.
		private List<NetworkInterface> networkInterfaces = new ArrayList<>();

		/**
		 * Default constructor.s
		 */
		public NetworkInformation() {

			super();
		}

		/**
		 * Provide: The IP number of the computer.
		 * 
		 * @return ipAddress The IP number of the computer.
		 */
		public byte[] getIpAddress() {

			return this.ipAddress;
		}

		/**
		 * Provide: The IP number of the computer with int values.
		 * 
		 * @return ipAddress The IP number of the computer.
		 */
		public int[] getIpAddressAsInt() {

			int ipAddressAsInt[] = new int[this.ipAddress.length];

			for (int i = 0; i < this.ipAddress.length; i++) {
				ipAddressAsInt[i] = this.ipAddress[i] < 0 ? 256 + this.ipAddress[i]
						: this.ipAddress[i];
			}

			return ipAddressAsInt;
		}

		/**
		 * Set: The IP number of the computer.
		 * 
		 * @param ipAddress
		 *            The IP number of the computer.
		 */
		public void setIpAddress(byte[] ipAddressParameter) {

			this.ipAddress = ipAddressParameter;
		}

		/**
		 * Provide: The host name of the computer.
		 * 
		 * @return hostName The host name of the computer.
		 */
		public String getHostName() {

			return this.hostName;
		}

		/**
		 * Set: The host name of the computer.
		 * 
		 * @param hostName
		 *            The host name of the computer.
		 */
		public void setHostName(String hostNameParameter) {

			this.hostName = hostNameParameter;
		}

		/**
		 * Provide: The host IP address in text format.
		 * 
		 * @return hostAddress The host IP address in text format.
		 */
		public String getHostAddress() {

			return this.hostAddress;
		}

		/**
		 * Set: The host IP address in text format.
		 * 
		 * @param hostAddress
		 *            The host IP address in text format.
		 */
		public void setHostAddress(String hostAddressParameter) {

			this.hostAddress = hostAddressParameter;
		}

		/**
		 * Provide: The canonical host name.
		 * 
		 * @return canonicalHostName The canonical host name.
		 */
		public String getCanonicalHostName() {

			return this.canonicalHostName;
		}

		/**
		 * Set: The canonical host name.
		 * 
		 * @param canonicalHostName
		 *            The canonical host name.
		 */
		public void setCanonicalHostName(String canonicalHostNameParameter) {

			this.canonicalHostName = canonicalHostNameParameter;
		}

		/**
		 * Provide: List of inet addresses.
		 * 
		 * @return inetAddress List of inet addresses.
		 */
		public List<InetAddress> getInetAddress() {

			return this.inetAddress;
		}

		/**
		 * Provide: List of all network interfaces.
		 * 
		 * @return networkInterfaces List of all network interfaces.
		 */
		public List<NetworkInterface> getNetworkInterfaces() {

			return this.networkInterfaces;
		}

		/**
		 * Set: List of all network interfaces.
		 * 
		 * @param networkInterfaces
		 *            List of all network interfaces.
		 */
		public void setNetworkInterfaces(
				Enumeration<NetworkInterface> networkInterfacesParameter) {

			while (networkInterfacesParameter.hasMoreElements()) {
				NetworkInterface interfaceElement = networkInterfacesParameter
						.nextElement();

				this.networkInterfaces.add(interfaceElement);
			}
		}

		/**
		 * Indicate if an Inet address is already stored.
		 * 
		 * @param address
		 *            The inet address.
		 * @return True if inet address exist.
		 */
		private boolean isInetAlreadyStored(InetAddress address) {

			for (InetAddress storedAddress : this.inetAddress) {
				if (storedAddress.equals(address)) {
					return true;
				}
			}

			return false;
		}

		/**
		 * Try to retrieve and populate the Inet addresses.
		 */
		public void getInetAddressInformation() {

			InetAddress[] addresses = null;

			try {
				addresses = InetAddress.getAllByName(null);
			} catch (UnknownHostException e) {
				// Do nothing;
			}

			if (addresses != null) {
				for (int i = 0; i < addresses.length; i++) {
					if (!isInetAlreadyStored(addresses[i])) {
						this.inetAddress.add(addresses[i]);
					}
				}
			}

			try {
				addresses = InetAddress.getAllByName(this.hostAddress);
			} catch (UnknownHostException e) {
				// Do nothing;
			}

			if (addresses != null) {
				for (int i = 0; i < addresses.length; i++) {
					if (!isInetAlreadyStored(addresses[i])) {
						this.inetAddress.add(addresses[i]);
					}
				}
			}

			try {
				addresses = InetAddress.getAllByName(this.canonicalHostName);
			} catch (UnknownHostException e) {
				// Do nothing;
			}

			if (addresses != null) {
				for (int i = 0; i < addresses.length; i++) {
					if (!isInetAlreadyStored(addresses[i])) {
						this.inetAddress.add(addresses[i]);
					}
				}
			}

			InetAddress theLocalhost = null;
			try {
				theLocalhost = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// Do nothing.
			}

			if (theLocalhost != null) {
				if (!isInetAlreadyStored(theLocalhost)) {
					this.inetAddress.add(theLocalhost);
				}
			}

		}

		/**
		 * Transform an address into a textual representation.
		 * 
		 * @param address
		 *            The address.
		 * @return The textual representation.
		 */
		public static String toIPAddressString(int address) {

			StringBuilder buffer = new StringBuilder();

			buffer.append(Integer.toString(0x000000ff & (address >> 24)));
			buffer.append("."); //$NON-NLS-1$
			buffer.append(Integer.toString(0x000000ff & (address >> 16)));
			buffer.append("."); //$NON-NLS-1$
			buffer.append(Integer.toString(0x000000ff & (address >> 8)));
			buffer.append("."); //$NON-NLS-1$
			buffer.append(Integer.toString(0x000000ff & (address)));

			return buffer.toString();
		}

		/**
		 * Transform the address into a textual representation.
		 * 
		 * @param address
		 *            The address.
		 * @return The textual representation.
		 */
		@SuppressWarnings("boxing")
		public static String toMACAddressString(byte[] address) {

			if (address == null) {
				return new String();
			}

			int iMax = address.length - 1;
			if (iMax == -1) {
				return new String();
			}

			StringBuilder buffer = new StringBuilder();
			buffer.append('[');
			for (int i = 0;; i++) {
				buffer.append(String.format("%1$02x", address[i])); //$NON-NLS-1$

				if (i == iMax) {
					return buffer.append(']').toString();
				}
				buffer.append(":"); //$NON-NLS-1$
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		public List<String> getAllIPv4Addresses() {

			List<String> allAddresses = new ArrayList<>();

			allAddresses.add(this.hostName);
			allAddresses.add(this.hostAddress);
			allAddresses.add(this.canonicalHostName);

			String address = new String();
			for (int i = 0; i < getIpAddressAsInt().length; i++) {
				address += getIpAddressAsInt()[i];
				if (i < this.ipAddress.length - 1) {
					address += "."; //$NON-NLS-1$
				}
			}
			allAddresses.add(address);

			for (InetAddress storedAddress : this.inetAddress) {

				allAddresses.add(storedAddress.getHostName());
				allAddresses.add(storedAddress.getHostAddress());
				allAddresses.add(storedAddress.getCanonicalHostName());

			}

			for (NetworkInterface networkInterface : this.networkInterfaces) {

				List<InterfaceAddress> interfaceAddresses = networkInterface
						.getInterfaceAddresses();

				for (InterfaceAddress interfaceElement : interfaceAddresses) {
					if (interfaceElement == null) {
						continue;
					}

					InetAddress theAddress = interfaceElement.getAddress();

					// allAddresses.add(theAddress.getHostName());
					allAddresses.add(theAddress.getHostAddress());
					allAddresses.add(theAddress.getCanonicalHostName());

				}

				Enumeration<InetAddress> inetAddresses = networkInterface
						.getInetAddresses();
				List<InetAddress> inetAddressesList = new ArrayList<>();
				while (inetAddresses.hasMoreElements()) {
					inetAddressesList.add(inetAddresses.nextElement());
				}

				for (InetAddress inetAddressElement : inetAddressesList) {

					allAddresses.add(inetAddressElement.getHostName());
					allAddresses.add(inetAddressElement.getHostAddress());
					allAddresses.add(inetAddressElement.getCanonicalHostName());

				}
			}

			List<String> uniqueAddresses = new ArrayList<>();

			for (String uniqueAddress : allAddresses) {

				boolean hasAddress = false;
				for (String storedAddress : uniqueAddresses) {

					if (storedAddress.equals(uniqueAddress)) {
						hasAddress = true;
						break;
					}
				}

				if (!hasAddress) {
					uniqueAddresses.add(uniqueAddress);

				}

			}

			return uniqueAddresses;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {

			StringBuffer out = new StringBuffer();

			out.append("NetworkInformation [\n"); //$NON-NLS-1$

			out.append("\thostName=" + this.hostName + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
			out.append("\thostAddress=" + this.hostAddress + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
			out.append("\tcanonicalHostName=" + this.canonicalHostName + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

			out.append("\tipAddress="); //$NON-NLS-1$
			for (int i = 0; i < getIpAddressAsInt().length; i++) {
				out.append(getIpAddressAsInt()[i]);
				if (i < this.ipAddress.length - 1) {
					out.append("."); //$NON-NLS-1$
				}
			}
			out.append("\n"); //$NON-NLS-1$

			out.append("\tinetAddress=\n"); //$NON-NLS-1$
			for (InetAddress storedAddress : this.inetAddress) {
				printInetAddress(out, storedAddress);
			}

			out.append("\tnetworkInterfaces=\n"); //$NON-NLS-1$

			for (NetworkInterface networkInterface : this.networkInterfaces) {

				out.append("\t\tDisplay name: " //$NON-NLS-1$
						+ networkInterface.getDisplayName() + "\n"); //$NON-NLS-1$
				out.append("\t\t\tName: " + networkInterface.getName() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

				String macAdress = new String();
				try {
					macAdress = NetworkInformation
							.toMACAddressString(networkInterface
									.getHardwareAddress());
				} catch (SocketException exception) {
					// Do nothing.
				}
				out.append("\t\t\tMAC: " + macAdress + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

				int mtu = -1;
				try {
					mtu = networkInterface.getMTU();
					out.append("\t\t\tMTU: " + mtu + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
				} catch (SocketException exception) {
					// Do nothing.
				}

				boolean supportMulticast = false;
				try {
					supportMulticast = networkInterface.supportsMulticast();
					out.append("\t\t\tSupport multicast: " //$NON-NLS-1$
							+ (supportMulticast ? "Yes" : "No") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				} catch (SocketException exception) {
					// Do nothing.
				}

				boolean loopBack = false;
				try {
					loopBack = networkInterface.isLoopback();
					out.append("\t\t\tIs loop backt: " //$NON-NLS-1$
							+ (loopBack ? "Yes" : "No") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				} catch (SocketException exception) {
					// Do nothing.
				}

				boolean pointToPoint = false;
				try {
					pointToPoint = networkInterface.isPointToPoint();
					out.append("\t\t\tIs point to point: " //$NON-NLS-1$
							+ (pointToPoint ? "Yes" : "No") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				} catch (SocketException exception) {
					// Do nothing.
				}

				out.append("\t\t\tVirtual: " //$NON-NLS-1$
						+ (networkInterface.isVirtual() ? "Yes" : "No") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

				boolean up = false;
				try {
					up = networkInterface.isUp();
					out.append("\t\t\tUp: " + (up ? "Yes" : "No") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				} catch (SocketException exception) {
					// Do nothing.
				}

				List<InterfaceAddress> interfaceAddresses = networkInterface
						.getInterfaceAddresses();

				out.append("\t\t\tNumber of addresses: " //$NON-NLS-1$
						+ interfaceAddresses.size() + "\n"); //$NON-NLS-1$

				int addressIndex = 0;
				for (InterfaceAddress interfaceElement : interfaceAddresses) {
					addressIndex++;

					if (interfaceElement == null) {
						continue;
					}

					out.append("\t\t\t\tAddress number: " + addressIndex //$NON-NLS-1$
							+ "\n"); //$NON-NLS-1$

					out.append("\t\t\t\t\tNetwork prefix: " //$NON-NLS-1$
							+ interfaceElement.getNetworkPrefixLength() + "\n"); //$NON-NLS-1$

					InetAddress broadcastAddress = interfaceElement
							.getBroadcast();
					if (broadcastAddress != null) {
						out.append("\t\t\t\t\tBroadcast: " //$NON-NLS-1$
								+ broadcastAddress.getHostAddress() + "\n"); //$NON-NLS-1$
					}

					int maskInt = Integer.MIN_VALUE >> (interfaceElement
							.getNetworkPrefixLength() - 1);
					out.append("\t\t\t\t\tMask: " //$NON-NLS-1$
							+ NetworkInformation.toIPAddressString(maskInt)
							+ "\n"); //$NON-NLS-1$

					InetAddress theAddress = interfaceElement.getAddress();

					out.append("\t\t\t\t\tHost: " + theAddress.getHostName() //$NON-NLS-1$
							+ "\n"); //$NON-NLS-1$
					out.append("\t\t\t\t\tClass: " //$NON-NLS-1$
							+ theAddress.getClass().getSimpleName() + "\n"); //$NON-NLS-1$

					out.append("\t\t\t\t\tHost address: " //$NON-NLS-1$
							+ theAddress.getHostAddress() + "\n"); //$NON-NLS-1$

					out.append("\t\t\t\t\tCHost: " //$NON-NLS-1$
							+ theAddress.getCanonicalHostName() + "\n"); //$NON-NLS-1$

					out.append("\t\t\t\t\tByte address: " //$NON-NLS-1$
							+ NetworkInformation.toMACAddressString(theAddress
									.getAddress()) + "\n"); //$NON-NLS-1$

					out.append("\t\t\t\t\tSite local: " //$NON-NLS-1$
							+ (theAddress.isSiteLocalAddress() ? "Yes" : "No") //$NON-NLS-1$ //$NON-NLS-2$
							+ "\n"); //$NON-NLS-1$
				}

				Enumeration<InetAddress> inetAddresses = networkInterface
						.getInetAddresses();
				List<InetAddress> inetAddressesList = new ArrayList<>();
				while (inetAddresses.hasMoreElements()) {
					inetAddressesList.add(inetAddresses.nextElement());
				}

				out.append("\t\t\tINET addresses:" + inetAddressesList.size() //$NON-NLS-1$
						+ "\n"); //$NON-NLS-1$

				for (InetAddress inetAddressElement : inetAddressesList) {
					printInetAddress(out, inetAddressElement);

				}
			}

			return out.toString();
		}

		/**
		 * @param out
		 * @param storedAddress
		 */
		private static void printInetAddress(StringBuffer out,
				InetAddress storedAddress) {
			out.append("\t\t\t\tTo String:" + storedAddress.toString() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

			out.append("\t\t\t\t\tHost: " + storedAddress.getHostName() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
			out.append("\t\t\t\t\tClass: " //$NON-NLS-1$
					+ storedAddress.getClass().getSimpleName() + "\n"); //$NON-NLS-1$
			out.append("\t\t\t\t\tIP: " + storedAddress.getHostAddress() //$NON-NLS-1$
					+ "\n"); //$NON-NLS-1$
			out.append("\t\t\t\t\tCanonical host name: " //$NON-NLS-1$
					+ storedAddress.getCanonicalHostName() + "\n"); //$NON-NLS-1$
			out.append("\t\t\t\t\tByte address: " //$NON-NLS-1$
					+ toMACAddressString(storedAddress.getAddress()) + "\n"); //$NON-NLS-1$
			out.append("\t\t\t\t\tSite local: " //$NON-NLS-1$
					+ storedAddress.isSiteLocalAddress() + "\n"); //$NON-NLS-1$
		}

	}

	/**
	 * Provide the network information.
	 * 
	 * @return The network information.
	 */
	public static NetworkInformation getNetworkInformation() {

		NetworkInformation networkInformation = new NetworkInformation();

		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException exception) {
			ExceptionSupport
					.handleException(InternalResource.get(
							InternalResource.NETWORK_EXCEPTION,
							exception.getMessage()));
			return networkInformation;
		}

		// Get IP Address
		byte[] ipAddress = address.getAddress();
		networkInformation.setIpAddress(ipAddress);

		String hostName = address.getHostName();
		networkInformation.setHostName(hostName);

		String hostAddress = address.getHostAddress();
		networkInformation.setHostAddress(hostAddress);

		String canonicalHostName = address.getCanonicalHostName();
		networkInformation.setCanonicalHostName(canonicalHostName);

		networkInformation.getInetAddressInformation();

		Enumeration<NetworkInterface> networkIntefaces = null;

		try {
			networkIntefaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException exception) {
			ExceptionSupport
					.handleException(InternalResource.get(
							InternalResource.NETWORK_EXCEPTION,
							exception.getMessage()));
			return networkInformation;
		}

		if (networkIntefaces != null) {
			networkInformation.setNetworkInterfaces(networkIntefaces);
		}

		return networkInformation;
	}

	/**
	 * Transform the address into a textual representation.
	 * 
	 * @param address
	 *            The address.
	 * @return The textual representation.
	 */
	@SuppressWarnings("boxing")
	public static String toMACAddressString(byte[] address) {

		if (address == null) {
			return new String();
		}

		int iMax = address.length - 1;
		if (iMax == -1) {
			return new String();
		}

		StringBuilder buffer = new StringBuilder();
		buffer.append('[');
		for (int i = 0;; i++) {
			buffer.append(String.format("%1$02x", address[i])); //$NON-NLS-1$

			if (i == iMax) {
				return buffer.append(']').toString();
			}
			buffer.append(":"); //$NON-NLS-1$
		}
	}

	/**
	 * Prepare the address to be used on the browser.
	 * 
	 * @param address
	 *            Any address.
	 * @return The address to be used on the browser.
	 */
	public static String formatIPv6(String address) {

		if (address == null) {
			return null;
		}

		int index = address.indexOf(':');

		if (index > 0) {

			int suffix = address.indexOf('%');

			if (suffix > 0) {

				String finalAddress = address.substring(0, suffix);

				return "[" + finalAddress + "]"; //$NON-NLS-1$ //$NON-NLS-2$
			}

			return "[" + address + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		}

		return address;
	}

}
