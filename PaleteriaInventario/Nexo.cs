using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
namespace PaleteriaInventario
{
    class Nexo
    {


        #region variables de instancia
        SqlConnection sqlConnection;
        #endregion

        #region Metodos

        /**
         * Este metodo se encarga de conectarse a la base de datos seleccionada mediante
         * un objeto SqlConnection utilizando la cadena de conexion y mediante el metodo
         * Open poder abrir un nexo entre el programa y la base de datos
         */
        public void conexion()
        {
            //Creamos el enlace con el objeto mediante la cadena de conexion
            sqlConnection = new SqlConnection("Data Source=DESKTOP-CM20RJA;Initial Catalog=Paleteria;Integrated Security=True");
            //Abrimos el nexo entre el programa y la base datos
            sqlConnection.Open();
        }

        public int obtenUltimoId(string tabla)
        {
            int id;
            SqlCommand command;
            SqlDataReader dataReader;
            id = -1;
            this.conexion();
            //Asignamos la conexion al sqlComand
            command = new SqlCommand("select top 1 idInventario from " + tabla + " order by idInventario desc;",this.sqlConnection);
            dataReader = command.ExecuteReader();
            dataReader.Read();
            id = int.Parse(dataReader[0].ToString());
            this.desConexion();
            return id;
        }


        /**
         * Ete metodo es el encargado de cerrar el nexo entre la base de datos y el 
         * programa mediante un objeto SqlConnection y el metodo Close
         */
        public void desConexion()
        {
            sqlConnection.Close();
        }

        /**
         * Este metodo es el encargado de ejecutar los comando SQL que deseamos para las operaciónes en nuestra base de datos
         * @Param SqlCommand sqlCommand sentencia preparada que ya contiene los datos a ejecutar
         */
        public void ejecutarSQL(SqlCommand sqlCommand, bool mensaje)
        {
            int cambios;

            //Nos conectamos a la base de datos
            this.conexion();

            //Asignamos la conexion al sqlComand
            sqlCommand.Connection = this.sqlConnection;
            //Ejecutamos la consutla SQL y guardamos el numero de filas afectadas
            cambios = sqlCommand.ExecuteNonQuery();

            //Verificamos los cambios en la base de
            if (mensaje)
            {
                if (cambios != 0)
                {
                    MessageBox.Show("Operacion realizada correctamente", "Exito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                else
                {
                    MessageBox.Show("No se pudo realizar la operación", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            this.desConexion();
        }

        /**
         * @Param DataGridView Es el data grid que queremos rellenar
         * @Param String Consulta que deseamos inicializar
         * Este metodo se encarga de llenar el datagrid 
         * 
         */
        public void actualizaGrid(DataGridView dataGridView, string consulta, string tabla)
        {
            //Cache de datos en memoria
            DataSet dataSet;
            //Adaptador de resultados de datosSQL para la variable DataSet
            SqlDataAdapter sqlDataAdapter;

            //Nos conectamos a la base de datos
            this.conexion();
            dataSet = new DataSet();


            //Inicializamos el adaptador con el comando SQL que deseamos realizar y con el objeto que tiene nuestro enlace
            sqlDataAdapter = new SqlDataAdapter(consulta, this.sqlConnection);

            //Utilizamos el metodo Fill para ejecutar el comando SQL y se ordenene en la variable dataSet
            sqlDataAdapter.Fill(dataSet, tabla);
            //Rellenamos los datos del dataGrid con el contenido del dataSet
            dataGridView.DataSource = dataSet;
            //Establecemos el nombre de la tabla de origen de los datos
            dataGridView.DataMember = tabla;

            //Nos desconectamos de la base de datos
            this.desConexion();

        }

        #endregion

    }
}
