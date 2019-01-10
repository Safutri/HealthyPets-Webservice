package com.admin.healthypets.servlet;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.server.spi.response.NotFoundException;
import com.admin.healthypets.pojo.Admin;
import com.admin.healthypets.pojo.DokterHewan;
import com.admin.healthypets.pojo.Klinik;
import com.admin.healthypets.pojo.Pemakai;
import com.admin.healthypets.pojo.Pemilik;
import com.admin.healthypets.pojo.Resepsionis;  
 
public class RestService { 
	private static final Logger log = Logger.getLogger(RestService.class.getName());
	private static final Pemakai pemakai = null;
	private final static String BASE_PATH="https://admin-web-dot-healthypets-webservice.appspot.com/_ah/api/adminapp/";
	
	public void postAdmin(String nama, String username, String email, String password) {
		// Asumsikan kembalian dari postMahasiswa ke API adalah objek json dari
		// mhs yang telah dibuat
		// Dosen dosen = null;
		try {

			URL url = new URL("https://healthypets-webservice.appspot.com/_ah/api/adminapp/register/admin");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			// payload untuk request post
			JSONObject payload = new JSONObject().put("nama", nama).put("username", username).put("email", email).
					put("password", password);

			// Eksekusi pemanggilan API
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(payload.toString());
			writer.close();

			// Baca response dari server
			// jika sukses menambah mahasiswa, response server adalah 200 / OK
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				log.warning("Gagal melakukan POST Data Admin");
				return;
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String line;
				StringBuffer response = new StringBuffer();

				System.out.println("Output from Server .... \n");
				while ((line = br.readLine()) != null) {
					response.append(line);
				}
				br.close();
				conn.disconnect();
				// ObjectMapper mapper = new ObjectMapper();
				// Konversi JSON ke POJO
				// dosen = mapper.readValue(response.toString(), Dosen.class);
			}

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return;
	}
	
//	public void postLogin(String email, String password) {
//		// Asumsikan kembalian dari postMahasiswa ke API adalah objek json dari
//		// mhs yang telah dibuat
//		// Dosen dosen = null;
//		try {
//
//			URL url = new URL("https://healthypets-webservice.appspot.com/_ah/api/adminapp/register/admin");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Type", "application/json");
//
//			// payload untuk request post
//			JSONObject payload = new JSONObject().put("email", email).put("password", password);
//
//			// Eksekusi pemanggilan API
//			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//			writer.write(payload.toString());
//			writer.close();
//
//			// Baca response dari server
//			// jika sukses menambah mahasiswa, response server adalah 200 / OK
//			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//				//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//				log.warning("Gagal melakukan Login Admin");
//				return;
//			} else {
//				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//				String line;
//				StringBuffer response = new StringBuffer();
//
//				System.out.println("Output from Server .... \n");
//				while ((line = br.readLine()) != null) {
//					response.append(line);
//				}
//				br.close();
//				conn.disconnect();
//				// ObjectMapper mapper = new ObjectMapper();
//				// Konversi JSON ke POJO
//				// dosen = mapper.readValue(response.toString(), Dosen.class);
//			}
//
//			
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//
//		}
//		return;
//	}
	
	// getAllPemilikKlinik 
		public List<Pemilik> getListPemilik() throws NotFoundException {
			List<Pemilik> pemakai = new ArrayList<>();
			// lakukan request
			try {
				URL url = new URL(BASE_PATH+"tampil");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				if (conn.getResponseCode() != 200) {
					//throw new RuntimeException("Gagal panggil API: " + conn.getResponseCode());
					return pemakai;
				} else {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					

					String response;
					StringBuilder stringBuilder = new StringBuilder();
					while ((response = br.readLine()) != null) {
						// simpan ke DaftarPemilik
						stringBuilder.append(response);
					}
					// gabungkan string yang telah diperoleh
					response = stringBuilder.toString();
					System.out.println("Response dari API: " + response);
					// periksa apakah kembalian punya arrayNode "daftarPemilik"
					final JsonNode arrNode = new ObjectMapper().readTree(response).get("items");
					if (arrNode != null && arrNode.isArray()) {
						Pemilik pemilik = null;
						for (final JsonNode objNode : arrNode) {
							if (objNode != null) {
								pemilik = new Pemilik(objNode.get("id").asText(), objNode.get("nama").asText(), objNode.get("alamat").asText(),
										objNode.get("email").asText(), objNode.get("identitas").asText(),
										objNode.get("telp").asText());
								pemakai.add(pemilik);	
							}
						}
					} else {
						throw new NotFoundException("Daftar Pemilik tidak ditemukan");
					}
				}			
				conn.disconnect();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return pemakai;
		} 
		
		public void postPemilik(String nama, String alamat, String email, String identitas, String telp) {
			try {

				URL url = new URL(BASE_PATH+"buat");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");

				// payload untuk request post
				JSONObject payload = new JSONObject().put("nama", nama).put("alamat", alamat).put("email", email).
						put("identitas", identitas).put("telp", telp);

				// Eksekusi pemanggilan API
				OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write(payload.toString());
				writer.close();

				// Baca response dari server
				// jika sukses menambah mahasiswa, response server adalah 200 / OK
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					log.warning("Gagal melakukan POST Data Pemilik");
					return;
				} else {
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

					String line;
					StringBuffer response = new StringBuffer();

					System.out.println("Output from Server .... \n");
					while ((line = br.readLine()) != null) {
						response.append(line);
					}
					br.close();
					conn.disconnect();
					// ObjectMapper mapper = new ObjectMapper();
					// Konversi JSON ke POJO
					// dosen = mapper.readValue(response.toString(), Dosen.class);
				}

				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();

			}
			return;
		}
		
		
		public Pemilik getPemilikById(Long idPemilik) {
			Pemilik result = null;
			try {
				URL url = new URL(BASE_PATH+"ambil?id=" + idPemilik);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Content-Type", "application/json");
				if (conn.getResponseCode() != 200) {
					return result;
				} else {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					String response;
					StringBuilder stringBuilder = new StringBuilder();
					while ((response = br.readLine()) != null) {
						// simpan ke DaftarFakultas
						stringBuilder.append(response);
					}
					// gabungkan string yang telah diperoleh
					response = stringBuilder.toString();
					System.out.println("Response dari API (Pemilik By id): " + response);
					final JsonNode objNode = new ObjectMapper().readTree(response);
					br.close();
					conn.disconnect();
 
					result = new Pemilik(
							objNode.get("id").asText(), 
							objNode.get("nama").asText(),
							objNode.get("alamat").asText(), 
							objNode.get("email").asText(), 
							objNode.get("identitas").asText(),
							objNode.get("telp").asText());
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
 
		public void CreateKlinik(Long idKlinik, String nama, String alamat, String email, String telp, String identitas) {
			try {
				URL url = new URL("https://healthypets-webservice.appspot.com/_ah/api/adminapp/buatklinik");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");

				// payload untuk request post
				JSONObject payload = new JSONObject() 
						.put("id", idKlinik)
						.put("nama", nama)
						.put("alamat", alamat)
						.put("email", email)
						.put("telp", telp)
						.put("identitas", identitas);

				log.warning(payload.toString());
				log.warning(url.toString());

				// Eksekusi pemanggilan API
				OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write(payload.toString());
				writer.close();

				// Baca response dari server
				// jika sukses menambah mahasiswa, response server adalah 200 / OK
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					log.warning("Response dari API: " + conn.getResponseMessage());
					//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					log.warning("Gagal menambahkan ruang");
					return;
				} else {
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

					String line;
					StringBuffer response = new StringBuffer();

					while ((line = br.readLine()) != null) {
						response.append(line);
					}

					br.close();
				}
				conn.disconnect();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		
		// getAllKlinik 
				public List<Klinik> getListKlinik() throws NotFoundException {
					List<Klinik> result = new ArrayList<>();
					// lakukan request
					try {
						URL url = new URL(BASE_PATH+"all/klinik");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						conn.setRequestProperty("Content-Type", "application/json");
						if (conn.getResponseCode() != 200) {
							return result;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
							

							String response;
							StringBuilder stringBuilder = new StringBuilder();
							while ((response = br.readLine()) != null) {
								// simpan ke DaftarPemilik
								stringBuilder.append(response);
							}
							// gabungkan string yang telah diperoleh
							response = stringBuilder.toString();
							System.out.println("Response All Klinik: " + response);
							// periksa apakah kembalian punya arrayNode "daftarPemilik"
							final JsonNode arrNode = new ObjectMapper().readTree(response).get("items");
							if (arrNode != null && arrNode.isArray()) {
								Klinik klinik = null;
								for (final JsonNode objNode : arrNode) {
									if (objNode != null) {
										klinik = new Klinik(objNode.get("id").asText(), objNode.get("nama").asText(), 
												objNode.get("alamat").asText(),	objNode.get("email").asText(), 
												 objNode.get("telp").asText(), pemakai,
												 objNode.get("identitas").asText());
										result.add(klinik);	
									}
								}
							} else {
								throw new NotFoundException("Daftar Klinik tidak ditemukan");
							}
						}			
						conn.disconnect();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result;
				} 				
				
				public List<Klinik> getDaftarKlinik(String idPemilik) {
					List<Klinik> result = new ArrayList<>();
					try {
						URL url = new URL(BASE_PATH+"daftar?id=" + idPemilik);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						if (conn.getResponseCode() != 200) {
							//throw new RuntimeException("Gagal panggil API: " + conn.getResponseCode());
							return result;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

							String response;
							StringBuilder stringBuilder = new StringBuilder();
							while ((response = br.readLine()) != null) {
								// simpan ke DaftarFakultas
								stringBuilder.append(response);

							}
							// gabungkan string yang telah diperoleh
							response = stringBuilder.toString();
							System.out.println("Response klinik by pemilik: " + response);
							// periksa apakah kembalian punya arrayNode "daftarFakultas"
							final JsonNode arrNode = new ObjectMapper().readTree(response).get("items");
							if (arrNode.isArray()) {
								Klinik klinik = null;
								for (final JsonNode objNode : arrNode) {
									klinik = new Klinik(objNode.get("id").asText(), objNode.get("nama").asText(), 
											objNode.get("alamat").asText(),	objNode.get("email").asText(), 
											 objNode.get("telp").asText(), pemakai,
											 objNode.get("identitas").asText());
									result.add(klinik);	
								}
							}
						}			
						conn.disconnect();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result;
				}

				public Klinik getKlinikById(Long idKlinik) {
					Klinik result = null;
					try {
						URL url = new URL(BASE_PATH+"klinik?id=" + idKlinik);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						conn.setRequestProperty("Content-Type", "application/json");
						if (conn.getResponseCode() != 200) {
							return result;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

							String response;
							StringBuilder stringBuilder = new StringBuilder();
							while ((response = br.readLine()) != null) {
								// simpan ke DaftarFakultas
								stringBuilder.append(response);
							}
							// gabungkan string yang telah diperoleh
							response = stringBuilder.toString();
							System.out.println("Response Klinik by id: " + response);
							final JsonNode objNode = new ObjectMapper().readTree(response);
							br.close();
							conn.disconnect();
		 
							result = new Klinik(objNode.get("id").asText(), objNode.get("nama").asText(), 
									objNode.get("alamat").asText(),	objNode.get("email").asText(), 
									 objNode.get("telp").asText(), pemakai,
									 objNode.get("identitas").asText());
						}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result;
				}
				
				public void CreateResepsionis(String nama, Long id, String alamat, String email, String telp, String nama_klinik,
						String email_klinik) {
					try {
						URL url = new URL("https://healthypets-webservice.appspot.com/_ah/api/adminapp/resepsionis/buat");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setDoOutput(true);
						conn.setRequestMethod("POST");
						conn.setRequestProperty("Content-Type", "application/json");

						// payload untuk request post
						JSONObject payload = new JSONObject() 
								.put("nama", nama)
								.put("id", id)
								.put("alamat", alamat)
								.put("email", email)
								.put("telp", telp)
								.put("nama_klinik", nama_klinik)
								.put("email_klinik", email_klinik);

						log.warning(payload.toString());
						log.warning(url.toString());

						// Eksekusi pemanggilan API
						OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
						writer.write(payload.toString());
						writer.close();

						// Baca response dari server
						// jika sukses menambah mahasiswa, response server adalah 200 / OK
						if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
							log.warning("Response dari API: " + conn.getResponseMessage());
							//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
							log.warning("Gagal menambahkan ruang");
							return;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

							String line;
							StringBuffer response = new StringBuffer();

							while ((line = br.readLine()) != null) {
								response.append(line);
							}

							br.close();
						}
						conn.disconnect();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
				
//				Resepsionis by Klinik id
				public List<Resepsionis> getDaftarResepsionis(String idResepsionis) {
					List<Resepsionis> result = new ArrayList<>();
					try {
						URL url = new URL(BASE_PATH+"resepsionis/list?id=" + idResepsionis);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						if (conn.getResponseCode() != 200) {
							//throw new RuntimeException("Gagal panggil API: " + conn.getResponseCode());
							return result;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

							String response;
							StringBuilder stringBuilder = new StringBuilder();
							while ((response = br.readLine()) != null) {
								// simpan ke DaftarFakultas
								stringBuilder.append(response);

							}
							// gabungkan string yang telah diperoleh
							response = stringBuilder.toString();
							System.out.println("Response Resepsionis by id klinik: " + response);
							// periksa apakah kembalian punya arrayNode "daftarFakultas"
							final JsonNode arrNode = new ObjectMapper().readTree(response).get("items");
							if (arrNode.isArray()) {
								Resepsionis resepsionis = null;
								for (final JsonNode objNode : arrNode) {
									resepsionis = new Resepsionis(objNode.get("nama").asText(), objNode.get("id").asLong(), 
											objNode.get("alamat").asText(),	objNode.get("email").asText(), 
											 objNode.get("telp").asText(), objNode.get("nama_klinik").asText(),
											 objNode.get("email_klinik").asText());
									result.add(resepsionis);	
								}
							}
						}			
						conn.disconnect();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result;
				}
				
				public Resepsionis getResepsionisById(Long idResepsionis) {
					Resepsionis result = null;
					try {
						URL url = new URL("https://20180905t124239-dot-healthypets-webservice.appspot.com/_ah/api/adminapp/get/resepsionis?id="+idResepsionis);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						conn.setRequestProperty("Content-Type", "application/json");
						if (conn.getResponseCode() != 200) {
							return result;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

							String response;
							StringBuilder stringBuilder = new StringBuilder();
							while ((response = br.readLine()) != null) {
								// simpan ke DaftarFakultas
								stringBuilder.append(response);
							}
							// gabungkan string yang telah diperoleh
							response = stringBuilder.toString();
							System.out.println("Response Resepsionis By id: " + response);
							final JsonNode objNode = new ObjectMapper().readTree(response);
							br.close();
							conn.disconnect();
		 
							result = new Resepsionis(objNode.get("nama").asText(), objNode.get("id").asLong(), 
											objNode.get("alamat").asText(),	objNode.get("email").asText(), 
											objNode.get("telp").asText(), objNode.get("nama_klinik").asText(),
											objNode.get("email_klinik").asText());
						}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result;
				}
		 
				
				public DokterHewan getDokterHewanById(Long idDokterHewan) {
					DokterHewan result = null;
					try {
						URL url = new URL(BASE_PATH+"ambil/drh?id="+idDokterHewan);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						conn.setRequestProperty("Content-Type", "application/json");
						if (conn.getResponseCode() != 200) {
							return result;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

							String response;
							StringBuilder stringBuilder = new StringBuilder();
							while ((response = br.readLine()) != null) {
								// simpan ke DaftarFakultas
								stringBuilder.append(response);
							}
							// gabungkan string yang telah diperoleh
							response = stringBuilder.toString();
							System.out.println("Response Resepsionis By id: " + response);
							final JsonNode objNode = new ObjectMapper().readTree(response);
							br.close();
							conn.disconnect(); 
							
							result = new DokterHewan(objNode.get("nama").asText(), objNode.get("id").asText(), 
											objNode.get("alamat").asText(),	objNode.get("email").asText(), 
											objNode.get("no_praktik").asText(), objNode.get("telp").asText(), 
											objNode.get("email_klinik").asText(),
											objNode.get("nama_klinik").asText());
						}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result;
				}
				
				public void CreateDokterHewan(String nama, Long idResepsionis, String alamat, String email, String no_praktik,
						String telp, String email_klinik, String nama_klinik) {
					try {
						URL url = new URL(BASE_PATH+"create/DokterHewan");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setDoOutput(true);
						conn.setRequestMethod("POST");
						conn.setRequestProperty("Content-Type", "application/json");

						// payload untuk request post
						JSONObject payload = new JSONObject() 
								.put("nama", nama)
								.put("id", idResepsionis)
								.put("alamat", alamat)
								.put("email", email)
								.put("no_praktik", no_praktik)
								.put("telp", telp)
								.put("nama_klinik", nama_klinik)
								.put("email_klinik", email_klinik);

						log.warning(payload.toString());
						log.warning(url.toString());

						// Eksekusi pemanggilan API
						OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
						writer.write(payload.toString());
						writer.close();

						// Baca response dari server
						// jika sukses menambah mahasiswa, response server adalah 200 / OK
						if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
							log.warning("Response dari API: " + conn.getResponseMessage());
							//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
							log.warning("Gagal menambahkan ruang");
							return;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

							String line;
							StringBuffer response = new StringBuffer();

							while ((line = br.readLine()) != null) {
								response.append(line);
							}

							br.close();
						}
						conn.disconnect();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
				
				
//				DokterHewan by Resepsionis id
				public List<DokterHewan> getDaftarDokterHewan(Long idResepsionis) {
					List<DokterHewan> result = new ArrayList<>();
					try {
						URL url = new URL(BASE_PATH+"list/drh?id=" + idResepsionis);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						if (conn.getResponseCode() != 200) {
							//throw new RuntimeException("Gagal panggil API: " + conn.getResponseCode());
							return result;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

							String response;
							StringBuilder stringBuilder = new StringBuilder();
							while ((response = br.readLine()) != null) {
								// simpan ke DaftarFakultas
								stringBuilder.append(response);

							}
							// gabungkan string yang telah diperoleh
							response = stringBuilder.toString();
							System.out.println("Response drh by id Resepsionis: " + response);
							// periksa apakah kembalian punya arrayNode "daftarFakultas"
							final JsonNode arrNode = new ObjectMapper().readTree(response).get("items");
							if (arrNode.isArray()) {
								DokterHewan drh = null;
								for (final JsonNode objNode : arrNode) {
									drh = new DokterHewan(objNode.get("nama").asText(), objNode.get("id").asText(), 
											objNode.get("alamat").asText(),	objNode.get("email").asText(), 
											objNode.get("no_praktik").asText(), objNode.get("telp").asText(),
											objNode.get("nama_klinik").asText(), objNode.get("email_klinik").asText());
									result.add(drh);	
								}
							}
						}			
						conn.disconnect();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result;
				}
				
				
				public void UpdatePemilik(Long idPemilik, String nama, String alamat, String identitas, String telp) {
					try {

						URL url = new URL(BASE_PATH+"update");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setDoOutput(true);
						conn.setRequestMethod("PUT");
						conn.setRequestProperty("Content-Type", "application/json");

						// payload untuk request post
						JSONObject payload = new JSONObject() 
								.put("id", idPemilik)
								.put("namaBaru", nama)
								.put("alamatBaru", alamat)
								.put("identitasBaru", identitas)
								.put("telpBaru", telp);

						// Eksekusi pemanggilan API
						OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
						writer.write(payload.toString());
						writer.close();

						// Baca response dari server
						// jika sukses menambah mahasiswa, response server adalah 200 / OK
						if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
							//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
							log.warning("Gagal melakukan UPDATE Data Pemilik");
							return;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

							String line;
							StringBuffer response = new StringBuffer();

							System.out.println("Output from Server .... \n");
							while ((line = br.readLine()) != null) {
								response.append(line);
							}
							br.close();
							conn.disconnect();
							// ObjectMapper mapper = new ObjectMapper();
							// Konversi JSON ke POJO
							// dosen = mapper.readValue(response.toString(), Dosen.class);
						}

						
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();

					}
					return;
				}
				
				
				public void UpdateKlinik(Long idKlinik, String nama, String alamat, String identitas, String email, String telp) {
					try {

						URL url = new URL("https://admin-web-dot-healthypets-webservice.appspot.com/_ah/api/adminapp/ubahklinik");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setDoOutput(true);
						conn.setRequestMethod("PUT");
						conn.setRequestProperty("Content-Type", "application/json");

						// payload untuk request post
						JSONObject payload = new JSONObject()
								.put("id", idKlinik)
								.put("namaBaru", nama)
								.put("alamatBaru", alamat)
								.put("praktikBaru", identitas)
								.put("emailBaru", email)
								.put("telpBaru", telp);

						// Eksekusi pemanggilan API
						OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
						writer.write(payload.toString());
						writer.close();

						// Baca response dari server
						// jika sukses menambah mahasiswa, response server adalah 200 / OK
						if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
							//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
							log.warning("Gagal melakukan UPDATE Data Pemilik");
							return;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

							String line;
							StringBuffer response = new StringBuffer();

							System.out.println("Output from Server .... \n");
							while ((line = br.readLine()) != null) {
								response.append(line);
							}
							br.close();
							conn.disconnect();
							// ObjectMapper mapper = new ObjectMapper();
							// Konversi JSON ke POJO
							// dosen = mapper.readValue(response.toString(), Dosen.class);
						}

						
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();

					}
					return;
				}
				 
				public void UpdateResepsionis(String idResepsionis, String nama, String alamat, String telp) {
					try {

						URL url = new URL("https://admin-web-dot-healthypets-webservice.appspot.com/_ah/api/adminapp/resepsionis/ubah");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setDoOutput(true);
						conn.setRequestMethod("PUT");
						conn.setRequestProperty("Content-Type", "application/json");

						// payload untuk request post
						JSONObject payload = new JSONObject()
								.put("id", idResepsionis)
								.put("namaBaru", nama)
								.put("alamatBaru", alamat)
								.put("telpBaru", telp);

						// Eksekusi pemanggilan API
						OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
						writer.write(payload.toString());
						writer.close();

						if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
							//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
							log.warning("Gagal melakukan UPDATE Data Resepsionis");
							return;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

							String line;
							StringBuffer response = new StringBuffer();

							System.out.println("Output from Server .... \n");
							while ((line = br.readLine()) != null) {
								response.append(line);
							}
							br.close();
							conn.disconnect(); 
						}

						
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();

					}
					return;
				}
				
				public void UpdateDokterHewan(Long idDrh, String nama, String alamat, String telp, String no_praktik, String email_klinik, String nama_klinik) {
					try {

						URL url = new URL(BASE_PATH+"drh/update");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setDoOutput(true);
						conn.setRequestMethod("PUT");
						conn.setRequestProperty("Content-Type", "application/json");

						// payload untuk request post
						JSONObject payload = new JSONObject()
								.put("id", idDrh)
								.put("namaBaru", nama)
								.put("alamatBaru", alamat)
								.put("telpBaru", telp)
								.put("no_praktikBaru", no_praktik) 
								.put("email_klinik_baru", email_klinik)
								.put("nama_klinik_baru", nama_klinik);

						// Eksekusi pemanggilan API
						OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
						writer.write(payload.toString());
						writer.close();

						// Baca response dari server
						// jika sukses menambah mahasiswa, response server adalah 200 / OK
						if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
							//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
							log.warning("Gagal melakukan UPDATE Data Dokter Hewan");
							return;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

							String line;
							StringBuffer response = new StringBuffer();

							System.out.println("Output from Server .... \n");
							while ((line = br.readLine()) != null) {
								response.append(line);
							}
							br.close();
							conn.disconnect();
							// ObjectMapper mapper = new ObjectMapper();
							// Konversi JSON ke POJO
							// dosen = mapper.readValue(response.toString(), Dosen.class);
						}

						
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();

					}
					return;
				}
				
				
				public Admin getAdminByEmail(String email) {
					Admin result = null;
					try {
						URL url = new URL(BASE_PATH+"adminHealthyPets?email=" + email);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						conn.setRequestProperty("Content-Type", "application/json");
						if (conn.getResponseCode() != 200) {
							return result;
						} else {
							BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

							String response;
							StringBuilder stringBuilder = new StringBuilder();
							while ((response = br.readLine()) != null) {
								// simpan ke DaftarFakultas
								stringBuilder.append(response);
							}
							// gabungkan string yang telah diperoleh
							response = stringBuilder.toString();
							System.out.println("Response dari API (Pemilik By id): " + response);
							final JsonNode objNode = new ObjectMapper().readTree(response);
							br.close();
							conn.disconnect();
		 
							result = new Admin(
									objNode.get("id").asText(), 
									objNode.get("nama").asText(),
									objNode.get("username").asText(), 
									objNode.get("email").asText(), 
									objNode.get("password").asText());
						}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result;
				}
				
//				public void CreateAdmin(String nama, String username, String email, String password) {
//					try {
//						URL url = new URL("https://healthypets-webservice.appspot.com/_ah/api/adminapp/Adminbaru");
//						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//						conn.setDoOutput(true);
//						conn.setRequestMethod("POST");
//						conn.setRequestProperty("Content-Type", "application/json");
//
//						// payload untuk request post
//						JSONObject payload = new JSONObject()  
//								.put("nama", nama)
//								.put("username", username)
//								.put("email", email)
//								.put("password", password);
//
//						log.warning(payload.toString());
//						log.warning(url.toString());
//
//						// Eksekusi pemanggilan API
//						OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//						writer.write(payload.toString());
//						writer.close();
//
//						// Baca response dari server
//						// jika sukses menambah mahasiswa, response server adalah 200 / OK
//						if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//							log.warning("Response dari API: " + conn.getResponseMessage());
//							//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//							log.warning("Gagal menambahkan ruang");
//							return;
//						} else {
//							BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//							String line;
//							StringBuffer response = new StringBuffer();
//
//							while ((line = br.readLine()) != null) {
//								response.append(line);
//							}
//
//							br.close();
//						}
//						conn.disconnect();
//					} catch (MalformedURLException e) {
//						e.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					return;
//				}
//				
}
