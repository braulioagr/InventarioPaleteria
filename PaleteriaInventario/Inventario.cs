using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PaleteriaInventario
{
    public partial class Inventario : Form
    {
        #region Variables de Instancia
        private Nexo nexo;
        private int idCliente;
        #endregion

        #region Constructores

        public Inventario()
        {
            InitializeComponent();
        }
        private void Inventario_Load(object sender, EventArgs e)
        {
            //Creamos el nexo
            this.idCliente = -1;
            this.nexo = new Nexo();
            //Actualizamos el datagrid de Cliente
            this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado.Cliente", "Cliente");
        }

        #endregion

        #region Eventos

        #region menus


        /** 
         * Detecta el item pulsado dentro de la toolBar de clientes para saber cual operacion debe ejecutar.
         * @param sender objeto el cual disparo este evento
         * @param e argumento propio del evento
         */
        private void toolStripClientes_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            //Obtenemos el AccesibleName del item pulsado para saber que acciones tolbar
            string mensaje;
            switch(e.ClickedItem.AccessibleName)
            {
                case "Agregar":
                    AltaCliente alta;
                    alta = new AltaCliente();
                    //Construimos el objeto y lo mandamos llamar como dialogo
                    if (alta.ShowDialog().Equals(DialogResult.OK))
                    {
                        //Si el usuario dio Ok damos de alta el usuario
                        this.nexo.ejecutarSQL(alta.Comando);
                        this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado.Cliente", "Cliente");
                    }
                break;
                case "Actualizar":
                    //Verificamos
                    if (this.idCliente != -1)
                    {
                        //Concatenamos todos los valores dentro del datagrid para mostrar el disclaimer en el Messagebox
                        mensaje = dataGridViewCliente.CurrentRow.Cells[0].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[1].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[2].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[3].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[4].Value.ToString();
                        if (MessageBox.Show("El registro seleccionado es: \n" + mensaje + "\n ¿Es este el que desea modificar?",
                                            "Confirmacion", MessageBoxButtons.YesNo,MessageBoxIcon.Question).Equals(DialogResult.Yes))
                        {
                            mensaje = mensaje.Replace(" ", "");
                            ModificaCliente modifica;
                            //Construimos el objeto y lo mandamos llamar como dialogo y dividimos la cadena con los datos para rellenar el valor del datagrid
                            modifica = new ModificaCliente(mensaje.Split(','));
                            if (modifica.ShowDialog().Equals(DialogResult.OK))
                            {
                                //Si el usuario dio Ok damos de alta el usuario
                                this.nexo.ejecutarSQL(modifica.Comando);
                                this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado.Cliente", "Cliente");
                            }
                        }
                    }
                    break;
                case "Eliminar":
                    if (this.idCliente != -1)
                    {
                        mensaje = dataGridViewCliente.CurrentRow.Cells[0].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[1].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[2].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[3].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[4].Value.ToString();
                        if (MessageBox.Show("El registro seleccionado es: \n" + mensaje + "\n ¿Es este el que desea eliminar? (No se podran recuperar los datos)",
                                            "Confirmacion", MessageBoxButtons.YesNo, MessageBoxIcon.Question).Equals(DialogResult.Yes))
                        {
                            SqlCommand comando;
                            comando = new SqlCommand("delete from empleado.Cliente where idCliente = @id");
                            comando.Parameters.Add(new SqlParameter("@id", SqlDbType.BigInt));
                            comando.Parameters[0].Value = this.idCliente;
                            this.nexo.ejecutarSQL(comando);
                            this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado.Cliente", "Cliente");
                        }
                    }
                break;
            }
        }

        #endregion

        #region Tamaño

        /** 
         * Redimenciona los objetos dentro de la form segun unas constantes que relacionan el tamaño de los controles
         * con el tamaño de la form.
         * @param sender objeto el cual disparo este evento
         * @param e argumento propio del evento
         */
        private void Inventario_Resize(object sender, EventArgs e)
        {
            Size size;
            Point point;
            //Creamos un nuevo Size restandole la diferencia de tamaño que tiene la form con el tab control y los asignamos
            size = new Size(this.Size.Width - 40, this.Size.Height - 63);
            this.tabControl1.Size = size;
            //Creamos un nuevo Size restandole la diferencia de tamaño que tiene la form con todos los DataGridView y los asignamos
            size = new Size(this.Size.Width - 60, this.Size.Height - 183);
            this.dataGridViewCliente.Size = size;
            //Aqui poner todos los size de los datagrid todos guardan la misma relacion

            //Creamos un nuevo point restandole la diferencia de tamaño que tiene la form con todos los DataGridView y los asignamos
            point = new Point(this.Size.Width- 218, this.textBoxNombreCliente.Location.Y);
            this.textBoxNombreCliente.Location = point;
            //Aqui poner todos los location de los botones de busqueda todos guardan la misma relacion

            //Creamos un nuevo point restandole la diferencia de tamaño que tiene la form con todos los label de los textBox de busqueda y los asignamos
            point = new Point(this.textBoxNombreCliente.Location.X-3, this.textBoxNombreCliente.Location.Y-16);
            this.label1.Location = point;
            //Aqui poner todos los location de los label de busqueda todos guardan la misma relacion
        }
        
        #endregion

        #region TextBox

        /** 
         * Detecta cuando se levanta una tecla en algun textbox de busqueda para asi mandar hacer una busqueda de coincidencias en la BD
         * @param sender objeto el cual disparo este evento
         * @param e argumento propio del evento
         */
        private void busqueda_keyUp(object sender, KeyEventArgs e)
        {
            string nombre;
            string tabla;
            DataGridView dataGrid;
            nombre = "";
            dataGrid = null;
            //Obtenemos el accesible name del textbox para saber en cual tabla vamos a realizar la busqueda
            tabla = ((TextBox)sender).AccessibleName;
            switch (tabla)
            {
                case "Cliente":
                    //Asignamos el valor de la busqeda del textbox Cliente al nombre y el datagrid al datagrid del cliente
                    //Ya que en esta tabla vamos a trabajar
                    dataGrid = this.dataGridViewCliente;
                    nombre = this.textBoxNombreCliente.Text;
                break;
            }
            if (dataGrid != null)
            {
                //Mandamos actualizar el data grid con una consulta que permita realizar busqueda por nombre
                this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado."+ tabla + " where nombreCliente like '" + nombre + "%';" , tabla);
            }
        }

        #endregion

        #region DataGRid

        private void dataGridViewCliente_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            this.idCliente = int.Parse(dataGridViewCliente.CurrentRow.Cells[0].Value.ToString());
        }

        #endregion

        #endregion

    }
}
