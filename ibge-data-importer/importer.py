import psycopg2
import pandas as pd

def insert_values(table, columns, values):
    placeholders = ', '.join(['%s'] * len(values))
    columns_formatted = ', '.join(columns)
    sql = f"INSERT INTO {table} ({columns_formatted}) VALUES ({placeholders})"
    cursor.execute(sql, values)
    conn.commit()

conn = psycopg2.connect(database="processor",
                        host="localhost",
                        user="admin",
                        password="admin",
                        port="5432")


cursor = conn.cursor()

# Read the Excel file
df = pd.read_excel('./tabela9514.xlsx')

# Initialize an empty list to store the objects
data = []

# Process each row in the dataframe
for index, row in df.iterrows():
    if index <= 7:  # Skip the first 7 rows
        continue
    # Extract the municipality name
    name = row['Município']
    
    # Sum columns E and F (assuming these are the 5th and 6th columns in Excel)
    # Excel columns are 1-indexed, but pandas is 0-indexed
    col_e_value = row.iloc[4]  # Column E (index 4)
    col_f_value = row.iloc[5]  # Column F (index 5)
    porc = col_e_value + col_f_value
    
    # Create object and append to the list
    data.append({
        'name': name,
        'porc': porc
    })
