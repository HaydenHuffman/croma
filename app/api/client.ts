import { Platform } from 'react-native';
import { fetchWithAuth } from './auth';

// Use 10.0.2.2 for Android emulator to access localhost, and localhost for iOS/Web.
// You may need to change this to your computer's local network IP if testing on a physical device.
const API_BASE_URL = Platform.OS === 'android' ? 'http://10.0.2.2:8080' : 'http://localhost:8080';

export interface Client {
  id: number;
  name: string;
  email: string;
  phone: string;
  companyName: string;
}

export const fetchClients = async (): Promise<Client[]> => {
  const response = await fetchWithAuth('/clients');
  if (!response.ok) {
    throw new Error('Failed to fetch clients');
  }
  return response.json();
};

export const createClient = async (client: Omit<Client, 'id'>): Promise<Client> => {
  const response = await fetchWithAuth('/clients', {
    method: 'POST',
    body: JSON.stringify(client),
  });
  if (!response.ok) {
    throw new Error('Failed to create client');
  }
  return response.json();
};
