<?php

namespace App\Models;

use App\Http\Controllers\CategorieMaterielController;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Materiel extends Model
{
    use HasFactory;
     protected $table='materiels';
    protected $fillable = [
        "titre",
        "quantité",
        "description",
        "idCategorie",
        "idResponsable",
    ];

    public function categorie(){
        return $this->hasOne(CategorieMateriel::class,'idCategorie');
        // hasMany(CategorieMateriel::class,'idCategorie');
    }
    // public function demande(){
    //     return $this->hasMany(De::class,'')
    // }
    // this Materiel belongsToMany DemandeMateriel
    public function demande(){
        return $this->belongsToMany(
            DemandeMateriel::class,
            'demande_materiel__materiels',
            'idMateriel',
            'idDemande')->withPivot(['quantité'])
            ;
    }


}
