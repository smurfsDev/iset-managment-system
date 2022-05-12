<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DemandeMateriel extends Model
{
    use HasFactory;

    protected $table='demande_materiels';
    protected $fillable = [
        "idResponsableClub",
        "idDestinataire",
        "dateEmploi",
        "dateDeRemise",
        "idCategorie",
        "status",
        "reponse",
    ];

    public function materiel(){
        return $this->belongsToMany(
            Materiel::class,
            'demande_materiel__materiels',
            'idDemande',
            'idMateriel')->withPivot(['quantité'])
            ;
    }
    public function destinataire(){
        return $this->belongsTo(User::class,'idDestinataire','id');
    }


}
