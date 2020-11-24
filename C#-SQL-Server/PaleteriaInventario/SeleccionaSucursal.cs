using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PaleteriaInventario
{
    partial class SeleccionaSucursal : Form
    {
        #region Variables de instancia
        private Nexo nexo;
        private int id;
        #endregion

        #region Costructores
        public SeleccionaSucursal(Nexo nexo)
        {
            this.nexo = nexo;
            InitializeComponent();
        }

        private void SeleccionaSucursal_Load(object sender, EventArgs e)
        {
            this.id = -1;
            this.nexo.actualizaGrid(this.dataGridViewSucursal, "select * from empleado.Sucursal", "Sucursal");
            MessageBox.Show("Por favor seleccione la Sucursal a la que llegara la carga", "Importante");
        }
        #endregion
        
        #region gets & sets
        public int ID
        {
            get { return this.id; }
        }
        #endregion

        private void button1_Click(object sender, EventArgs e)
        {
            string mensaje;
            if (this.id != -1)
            {
                mensaje = this.dataGridViewSucursal.CurrentRow.Cells[0].Value.ToString() + " , " +
                    "\"" + this.dataGridViewSucursal.CurrentRow.Cells[1].Value.ToString() + "\"";
                if (MessageBox.Show("¿Usted quiere trabajar con la sucursal: [" + mensaje + "]", "Advertencia", MessageBoxButtons.YesNo, MessageBoxIcon.Exclamation).Equals(DialogResult.Yes))
                {
                    this.DialogResult = DialogResult.OK;
                    this.Close();
                }
            }
            else
            {
                MessageBox.Show("Por favor seleccione una sucursal","Advertencia", MessageBoxButtons.OK,MessageBoxIcon.Exclamation);
            }
        }

        private void dataGridViewSucursal_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            this.id = int.Parse(this.dataGridViewSucursal.CurrentRow.Cells[0].Value.ToString());
        }
    
    }
}
